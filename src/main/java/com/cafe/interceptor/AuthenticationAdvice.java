package com.cafe.interceptor;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.annotation.LoginRequired;
import com.cafe.service.error.AuthenticationException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationAdvice implements MethodInterceptor{
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationAdvice.class);
   
    @Autowired
    private HttpServletRequest request;
   
    @Autowired
    private HttpServletResponse response;
   
    @Value("CREATEDBYJIHO_AT")
    private String accessTokenSecretKey;
   
    @Value("CREATEDBYJIHO_RT")
    private String refreshTokenSecretKey;
    
    private static final int oneMinute = 60000;	// 토큰 만료기간 1분(milli 단위)

    public Object invoke(final MethodInvocation invocation) throws Throwable {
        Map<String, Object> result = null;
        final long curTime = System.currentTimeMillis();

        final Date accessExDate = new Date(System.currentTimeMillis() + oneMinute * 1); // 토큰 만료시간 30분
        final Date refreshExDate = new Date(System.currentTimeMillis() + oneMinute * 60 * 24 * 14); // 토큰 만료시간 2주

        try {
            String newAccessToken = null;
            String newRefreshToken = null;

            final Method method = invocation.getMethod();

            final LoginRequired anno = AnnotationUtils.findAnnotation(method, LoginRequired.class);

            // refresh token이 있을 경우 access token을 재발급 받는다.
            final String refreshToken = request.getHeader("RefreshToken");
            if (refreshToken != null) {
                try {
                    final Claims claims = Jwts.parser().setSigningKey(refreshTokenSecretKey.getBytes())
                            .parseClaimsJws(refreshToken).getBody();

                    // 엑세스 토큰 재설정
                    newAccessToken = Jwts.builder().setSubject("accessToken").setExpiration(accessExDate)
                            .claim("id", claims.get("id"))
                            .signWith(SignatureAlgorithm.HS256, accessTokenSecretKey.getBytes("UTF-8")).compact();

                    final Date refreshExpDate = claims.get("exp", Date.class);
                    if (true) {
                        // 리프래시 토큰 재설정
                        newRefreshToken = Jwts.builder().setSubject("refreshToken").setExpiration(refreshExDate)
                                .claim("id", claims.get("id"))
                                .signWith(SignatureAlgorithm.HS256, refreshTokenSecretKey.getBytes("UTF-8")).compact();
                    }
                } catch (final ExpiredJwtException e) {
                    throw new AuthenticationException("인증 오류가 발생하였습니다.");
                }
            }

            // access token으로 인증 처리를 진행하고 인증정보를 세팅한다.
            final String accessToken = newAccessToken != null ? newAccessToken : request.getHeader("AccessToken");
            if (accessToken == null) {
                throw new AuthenticationException("Access Token이 존재하지 않습니다.");
            }
            try {
                final Claims claims = Jwts.parser().setSigningKey(accessTokenSecretKey.getBytes())
                        .parseClaimsJws(accessToken).getBody();

                // 인증 정보 세팅
                final Map<String, Object> bundle = new HashMap<String, Object>();
                bundle.put("id", claims.get("id"));
                request.setAttribute("auth", bundle);

            } catch (final ExpiredJwtException e) {
                throw new AuthenticationException("인증 오류가 발생하였습니다.");
            }

            try {
                result = (Map<String, Object>) invocation.proceed();
                if (newAccessToken != null) {
                    result.put("accessToken", newAccessToken);
                }
                if (newRefreshToken != null) {
                    result.put("refreshToken", newRefreshToken);
                }
            } catch (final Exception e) {
                throw e;
            }
        } catch (final AuthenticationException e) {
            response.setStatus(400);
            return null;
        }
       
        return result;
    }
   
    public boolean isAuthenticated(final String secretKey, final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return true;
    }
   
    public Map<String, Object> setError(final int code, final String message) {
        response.setStatus(code);
        Map<String, Object> result = new HashMap<>();
        result.put(message, code);
        return result;
    }
}