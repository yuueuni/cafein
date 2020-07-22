package com.web.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.cafe.dao.LikeDao;
import com.web.cafe.dto.LikeDto;


@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDao dao;
	
	@Override
	public int count(int cafeno) {
		return dao.count(cafeno);
	}
	
	@Override
	public int insert(LikeDto like) {
		return dao.insert(like);
	}

	@Override
	public int delete(LikeDto like) {
		return dao.delete(like);
	}

	@Override
	public int selectByUser(LikeDto user) {
		return dao.selectByUser(user);
	}
	
}
