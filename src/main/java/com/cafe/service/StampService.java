package com.cafe.service;

import com.cafe.dto.StampDto;

public interface StampService {
	public int count(int cafeno);
	public int insert(StampDto stamp);
	public int delete(StampDto stamp);
	int selectByUser(StampDto user);
}
