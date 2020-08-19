package com.cafe.service.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dto.TokenSet;
import com.cafe.service.jwt.JwtService;
import com.cafe.service.jwt.JwtServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

@WebFilter(urlPatterns = {"*"})
public class AllowFilterChecker implements Filter{
    private static final String HEADER_ACCESS = "ACCESS_TOKEN";
	private static final String HEADER_REFRESH = "REFRESH_TOKEN";

	@Autowired
    private JwtService jwtService;
    
    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //들어온 요청에 대한 행동
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        
        String accessToken = req.getHeader(HEADER_ACCESS);
        final String refreshToken = req.getHeader(HEADER_REFRESH);
        
        if(refreshToken != null){
            jwtService = new JwtServiceImpl();

            TokenSet tokenSet = jwtService.refreshAccessToken(refreshToken);
            accessToken = tokenSet.getAccessToken();
            res.setContentType("application/json");
            res.setHeader(HEADER_ACCESS, accessToken);
            res.setHeader(HEADER_REFRESH, refreshToken);
            System.out.println(req.getRequestURL() + "in filter");
        }
        chain.doFilter(request, res);
    }

	@Override
	public void destroy() {
        System.out.println();
	}
}