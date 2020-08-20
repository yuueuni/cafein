package com.cafe.advice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class HttpResponseAdvice implements ResponseBodyAdvice<Object>{
    @Override
	public boolean supports(MethodParameter returnType,
	                        Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body,
	                              MethodParameter returnType,
	                              MediaType selectedContentType,
	                              Class<? extends HttpMessageConverter<?>> selectedConverterType,
	                              ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("beforeBodyWrite");
		//response.getHeaders().add("Server", "advice");
		try{
			response.getBody().write("Server".getBytes(StandardCharsets.UTF_8));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return body;
	}
}