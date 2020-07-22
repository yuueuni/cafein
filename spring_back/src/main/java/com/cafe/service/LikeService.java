package com.cafe.service;

import com.cafe.dto.LikeDto;

public interface LikeService {
	public int count(int cafeno);
	public int insert(LikeDto like);
	public int delete(LikeDto like);
	int selectByUser(LikeDto user);
}
