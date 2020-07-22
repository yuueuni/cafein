package com.web.cafe.dao;

import com.web.cafe.dto.LikeDto;

public interface LikeDao {
	public int count(int cafeno);
	public int insert(LikeDto like);
	public int delete(LikeDto like);
	int selectByUser(LikeDto user);
}
