package com.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dao.CafeDao;
import com.cafe.dto.CafeDto;


@Service
public class CafeServiceImpl implements CafeService {

	@Autowired
	private CafeDao dao;
	
	@Override
	public List<CafeDto> search(String keyword) {
		return dao.search(keyword);
	}
	
	@Override
	public CafeDto select(int cafeno) {
		return dao.select(cafeno);
	}

	@Override
	public List<CafeDto> selectAll(int page) {
		return dao.selectAll(page);
	}

	@Override
	public int insert(CafeDto cafe) {
		return dao.insert(cafe);
	}

	@Override
	public int update(CafeDto cafe) {
		return dao.update(cafe);
	}

	@Override
	public int delete(int cafeno) {
		return dao.delete(cafeno);
	}

	

}
