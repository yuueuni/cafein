package com.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.dto.CafeDto;



@Repository
public class CafeDaoImpl implements CafeDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public CafeDto select(int cafeno) {
		return session.selectOne("cafe.select", cafeno);
	}
	
	@Override
	public List<CafeDto> selectAll(int page) {
		int n = 10; //한번에 보여줄 데이터 갯수
		RowBounds bound = new RowBounds((page - 1) * n, n);
		return session.selectList("cafe.selectAll", null, bound);
	}

	@Override
	public int insert(CafeDto cafe) {
		return session.insert("cafe.insert", cafe);
	}

	@Override
	public int update(CafeDto cafe) {
		return session.update("cafe.update", cafe);
	}

	@Override
	public int delete(int cafeno) {
		return session.delete("cafe.delete", cafeno);
	}

}
