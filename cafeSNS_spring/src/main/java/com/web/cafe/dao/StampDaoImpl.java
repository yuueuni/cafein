package com.web.cafe.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.cafe.dto.StampDto;


@Repository
public class StampDaoImpl implements StampDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public int count(int cafeno) {
		return session.selectOne("stamp.count", cafeno);
	}

	@Override
	public int insert(StampDto stamp) {
		return session.insert("stamp.insert", stamp);
	}

	@Override
	public int delete(StampDto stamp) {
		return session.delete("stamp.delete", stamp);
	}
	
	@Override
	public int selectByUser(StampDto user) {
		return session.selectOne("stamp.selectByUser", user);
	}

}
