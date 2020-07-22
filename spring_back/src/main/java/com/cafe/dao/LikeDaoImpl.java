package com.cafe.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.dto.LikeDto;



@Repository
public class LikeDaoImpl implements LikeDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public int count(int cafeno) {
		return session.selectOne("like.count", cafeno);
	}

	@Override
	public int insert(LikeDto like) {
		return session.insert("like.insert", like);
	}

	@Override
	public int delete(LikeDto like) {
		return session.delete("like.delete", like);
	}
	
	@Override
	public int selectByUser(LikeDto user) {
		return session.selectOne("like.selectByUser", user);
	}

}
