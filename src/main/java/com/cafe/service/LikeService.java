package com.cafe.service;

import java.util.List;

import com.cafe.dto.CafeDto;
import com.cafe.dto.LikeDto;

public interface LikeService {
	public int count(int cafeno);
	public int insert(LikeDto like);
	public int delete(LikeDto like);
	public int selectByUser(LikeDto user);
	public List<CafeDto> selectCafe(String uid);
}
