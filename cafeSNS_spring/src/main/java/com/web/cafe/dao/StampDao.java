package com.web.cafe.dao;

import com.web.cafe.dto.StampDto;

public interface StampDao {
	public int count(int cafeno);
	public int insert(StampDto stamp);
	public int delete(StampDto stamp);
	int selectByUser(StampDto user);
}
