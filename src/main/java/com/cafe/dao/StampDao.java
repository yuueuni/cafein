package com.cafe.dao;

import java.util.List;

import com.cafe.dto.CafeDto;
import com.cafe.dto.StampDto;

public interface StampDao {
	public int count(int cafeno);
	public int insert(StampDto stamp);
	public int delete(StampDto stamp);
	int selectByUser(StampDto user);
	public List<CafeDto> selectCafe(String uid);
	public void update(CafeDto cafe);//카페의 카운트수 변경
}
