package com.cafe.dao;

import java.util.List;

import com.cafe.dto.CafeDto;

public interface RecommendDao {
	public List<CafeDto> selectCafeLiked(String uid);
	public List<String> selectUserLiked(int cafeno);
}
