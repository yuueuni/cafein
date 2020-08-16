package com.cafe.service;

import java.util.List;

import com.cafe.dto.CafeDto;


public interface RecommendService {
	public List<Integer> selectCafeLiked(String uid);
	public List<String> selectUserLiked(int cafeno);
}
