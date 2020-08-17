package com.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dao.RecommendDao;
import com.cafe.dto.CafeDto;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	private RecommendDao dao;
	
	@Override
	public List<Integer> selectCafeLiked(String uid) {
		return dao.selectCafeLiked(uid);
	}

	@Override
	public List<String> selectUserLiked(int cafeno) {
		return dao.selectUserLiked(cafeno);
	}

	@Override
	public List<Integer> selectCafeStamped(String uid) {
		return dao.selectCafeStamped(uid);
	}

	@Override
	public List<String> selectUserStamped(int cafeno) {
		return dao.selectUserStamped(cafeno);
	}

}