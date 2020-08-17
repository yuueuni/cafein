package com.cafe.dao;

import java.util.List;

import com.cafe.dto.CafeDto;
import com.cafe.dto.LikeDto;

public interface LikeDao {
	public int count(int cafeno);
	public int insert(LikeDto like);
	public int delete(LikeDto like);
	public int selectByUser(LikeDto user);
	public List<CafeDto> selectCafe(String uid);
	public void plusCount(int cafeno);//카페의 카운트수 증가
	public void minusCount(int cafeno);//카페의 카운트수 감소
}
