package com.web.cafe.service;

import com.web.cafe.dto.StampDto;

public interface StampService {
	public int count(int cafeno);
	public int insert(StampDto stamp);
	public int delete(StampDto stamp);
	int selectByUser(StampDto user);
}
