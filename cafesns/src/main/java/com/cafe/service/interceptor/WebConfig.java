package com.cafe.service.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	private static final String[] EXCLUDE_PATHS = { // 제외할 url 패턴
			"/api/user/*"
			};


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtInterceptor())
						.addPathPatterns("/api/post/*")
						.addPathPatterns("/api/cafe/*")
						.excludePathPatterns(EXCLUDE_PATHS);
	}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        		.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD");
    }
}
