package com.cafe.service.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	private static final String[] EXCLUDE_PATHS = { // 허용할 url 패턴
			"/api/user/*",
			"/swagger-ui.html"
			};
	private static final String[] INCLUDE_PATHS = { // 막을 url 패턴
			"/api/cafe",
			"/api/cafe/delete/*",
			"/comment",
			"/comment/delete/*",
			"/follow",
			"/follow/delete/*",
			"/like/check/*",
			"/like/delete/*",
			"/menu/delete/*",
			"/api/post",
			"/api/post/delete/*",
			"/stamp/check/*",
			"/stamp/delete/*",
			"/like/**"
	};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtInterceptor())
			.addPathPatterns(INCLUDE_PATHS)
			.excludePathPatterns(EXCLUDE_PATHS);
	}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        		.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD");
    }
}
