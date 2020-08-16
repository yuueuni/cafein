package com.cafe.service;

import java.util.List;

import com.cafe.dto.CafeDto;


public interface RecommendService {
	public List<CafeDto> selectCafeLiked(String uid);
	public List<String> selectUserLiked(int cafeno);
}
