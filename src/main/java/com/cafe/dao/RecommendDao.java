package com.cafe.dao;

import java.util.List;

import com.cafe.dto.CafeDto;

public interface RecommendDao {
	public List<Integer> selectCafeLiked(String uid);
	public List<String> selectUserLiked(int cafeno);
	public List<Integer> selectCafeStamped(String uid);
	public List<String> selectUserStamped(int cafeno);
}