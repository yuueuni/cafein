package com.cafe.service.jwt;

import java.util.Map;

public interface JwtService {
	<T> String create(String key, T data, String subject);
	Map<String,Object> get(String key);
	int getUserId();
	boolean isUsable(String jwt);
}
