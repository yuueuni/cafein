package com.cafe.service.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cafe.service.error.UnauthorizedException;
import com.cafe.service.jwt.JwtService;
import com.cafe.service.jwt.JwtServiceImpl;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	private static final String HEADER_AUTH = "Authorization";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		jwtService = new JwtServiceImpl();
		System.out.println("interceptor");
		final String token = request.getHeader(HEADER_AUTH);
		System.out.println("요청방식 : "+  request.getMethod());
		System.out.println("요청url :" + request.getRequestURI());
		
		System.out.println("token is   " + token);
//		Enumeration headerNames = request.getHeaderNames();

//		while(headerNames.hasMoreElements()) { 
//			   String name = (String)headerNames.nextElement();
//			   String value = request.getHeader(name);
//			   System.out.println(name +"   "+ value);
//			}
//		return false;

		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}
		 if(token != null && jwtService.isValidToken(token, jwtService.getKey())){
			 return true; 
		 }else{ 
			 throw new UnauthorizedException(); 
		}
		 
	}
}
