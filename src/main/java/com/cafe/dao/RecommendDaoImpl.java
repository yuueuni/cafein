package com.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.dto.CafeDto;

@Repository
public class RecommendDaoImpl implements RecommendDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<Integer> selectCafeLiked(String uid) {
		return session.selectList("recommend.selectCafeLiked", uid);
	}

	@Override
	public List<String> selectUserLiked(int cafeno) {
		return session.selectList("recommend.selectUserLiked", cafeno);
	}

}
