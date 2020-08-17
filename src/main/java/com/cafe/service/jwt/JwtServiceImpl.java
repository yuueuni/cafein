package com.cafe.service.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cafe.dto.TokenSet;
import com.cafe.dto.UserDto;
import com.cafe.service.error.AuthenticationException;
import com.cafe.service.error.JWTException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("jwtService")
public class JwtServiceImpl implements JwtService {

	//access token secret key
	public static final String AT_SECRET_KEY = "CREATEDBYJIHO_AT";
	//refresh token secret key
	private static final String RT_SECRET_KEY = "CREATEDBYJIHO_AT";
	private static final String DATA_KEY = "user";

	private static final String SALT =  "CafeSnsSecret";
	private static final int oneMinute = 60000;	// 토큰 만료기간 1분(milli 단위)

	@Override
	public TokenSet createTokenSet(UserDto user) {
		long curTime = System.currentTimeMillis();

		Date accessExDate = new Date(System.currentTimeMillis() + oneMinute*30); // 토큰 만료시간 30분
		Date refreshExDate = new Date(System.currentTimeMillis() + oneMinute*60*24*7); // 토큰 만료시간 일주일	
		TokenSet tokenSet = TokenSet.create().refreshToken(Jwts.builder()
				.setIssuer("cafein")
				.setExpiration(refreshExDate)
				.setIssuedAt(new Date(curTime))
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", curTime)
				.claim(DATA_KEY, user)
				.signWith(SignatureAlgorithm.HS256, this.generateKey(RT_SECRET_KEY))
				//.setSubject(subject)
				.compact());

		// refreshToke DB에 넣기 해야함
				
		return tokenSet.accessToken(Jwts.builder()
				.setIssuer("cafein")
				.setExpiration(accessExDate)
				.setIssuedAt(new Date(curTime))
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", curTime)
				.claim(DATA_KEY, user)
				.signWith(SignatureAlgorithm.HS256, this.generateKey(AT_SECRET_KEY))
				//.setSubject(subject)
				.compact());
	}

	@Override
	public TokenSet refreshAccessToken(String refreshToken) {
		long curTime = System.currentTimeMillis();
		//refreshToken의 만료기간이 남았는지 확인하고, 
		if(!isValidToken(refreshToken, RT_SECRET_KEY)) {
			throw new AuthenticationException("로그인되어있지 않습니다.");
		}

		//DB로부터 refreshToken 유효한지 조회
		// 유효 하지 않다면 AuthenticationException 발생시키기
		// Query query = new Query();
		// query.addCriteria(Criteria.where("refreshToken").is(refreshToken));
		// List<TokenSet> validToken = mongoOperations.find(query, TokenSet.class, "refreshToken");
		// if(validToken.isEmpty()) {
		// 	throw new AuthenticationException("로그인되어있지 않습니다.");
		// }
		
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(this.generateKey(RT_SECRET_KEY)).parseClaimsJws(refreshToken);
		} catch (Exception e) {
			throw new JWTException("decodeing failed");
		}
		
		//curTime이 refreshToken의 만료일 7일 이내면, refreshTokenSet 진행. 
		if(Long.parseLong(String.valueOf(claims.getBody().get("exp"))) * 1000 - curTime <= (1000*60*60*24*7)) {
			return refreshTokenSet(refreshToken);
		}
		return TokenSet.create()
				  .accessToken(Jwts.builder()
									 .setHeaderParam("typ", "JWT")
									 .setExpiration(new Date(curTime + (1000*60*30)))
									 .setIssuedAt(new Date(curTime))
									 .claim(DATA_KEY, getUser(refreshToken, RT_SECRET_KEY))
									 .signWith(SignatureAlgorithm.HS256, this.generateKey(AT_SECRET_KEY))
									 .compact());
	}

	@Override
	public TokenSet refreshTokenSet(String refreshToken) {
		return createTokenSet(getUser(refreshToken, RT_SECRET_KEY));
	}

	private byte[] generateKey(String secretKey) {
		byte[] key = null;
		try {
			key = secretKey.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return key;
	}

	@Override
	public UserDto getUser(String jwt, String secretKey) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
			.setSigningKey(this.generateKey(secretKey))
			.parseClaimsJws(jwt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JWTException("decodeing failed");
		}
		return new ObjectMapper().convertValue(claims.getBody().get(DATA_KEY), UserDto.class);
	}

	@Override
	public boolean isValidToken(String jwt, String secretKey) {
		try {
			Jwts.parser()
				.setSigningKey(this.generateKey(secretKey))
				.parseClaimsJws(jwt);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String getKey(){
		return AT_SECRET_KEY;
	}
	// @Override
	// public int getUserId() {
	// 	return (int)this.getUser("member").get("memberId");
	// }

	// 	@SuppressWarnings("unchecked")
// 	@Override
// 	public Map<String, Object> get(String key) {
// 		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
// 		String jwt = request.getHeader("Authorization");
// 		Jws<Claims> claims = null;
// 		try {
// 			claims = Jwts.parser()
// 						 .setSigningKey(SALT.getBytes("UTF-8"))
// 						 .parseClaimsJws(jwt);
// 			Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
// 			return value;
// 		} catch (Exception e) {
// 				e.printStackTrace();
// 				return null;
// 			/*개발환경
// 			Map<String,Object> testMap = new HashMap<>();
// 			testMap.put("memberId", 2);
// 			return testMap;*/
// 		}
// //		@SuppressWarnings("unchecked")
// //		Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
// //		return value;
// 	}


	// @Override
	// public boolean isUsable(String jwt) {
	// 	try{
	// 		Jws<Claims> claims = Jwts.parser()
	// 				  .setSigningKey(this.generateKey())
	// 				  .parseClaimsJws(jwt);
	// 		return true;
			
	// 	}catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// 	return false;
	// }
}
