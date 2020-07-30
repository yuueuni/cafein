package com.cafe.service;

import java.util.List;

import com.cafe.dto.CafeDto;
import com.cafe.dto.StampDto;

public interface StampService {
	public int count(int cafeno);
	public int insert(StampDto stamp);
	public int delete(StampDto stamp);
	int selectByUser(StampDto user);
	public List<CafeDto> selectCafe(String uid);
}
