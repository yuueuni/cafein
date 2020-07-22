package com.web.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.cafe.dao.LikeDao;
import com.web.cafe.dao.StampDao;
import com.web.cafe.dto.LikeDto;
import com.web.cafe.dto.StampDto;


@Service
public class StampServiceImpl implements StampService {

	@Autowired
	private StampDao dao;
	
	@Override
	public int count(int cafeno) {
		return dao.count(cafeno);
	}
	
	@Override
	public int insert(StampDto stamp) {
		return dao.insert(stamp);
	}

	@Override
	public int delete(StampDto stamp) {
		return dao.delete(stamp);
	}

	@Override
	public int selectByUser(StampDto user) {
		return dao.selectByUser(user);
	}
	
}
