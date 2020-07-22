package com.web.cafe.service;

import java.util.List;

import com.web.cafe.dto.CafeDto;

public interface CafeService {

	public CafeDto select(int cafeno);
	
	public List<CafeDto> selectAll(int page);
	
	public int insert(CafeDto cafe);
	
	public int update(CafeDto cafe);
	
	public int delete(int cafeno);
	
}
