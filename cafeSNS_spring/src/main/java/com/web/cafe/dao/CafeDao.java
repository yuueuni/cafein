package com.web.cafe.dao;

import java.util.List;

import com.web.cafe.dto.CafeDto;

public interface CafeDao {
	
	public CafeDto select(int cafeno);
	
	public List<CafeDto> selectAll(int page);
	
	public int insert(CafeDto cafe);
	
	public int update(CafeDto cafe);
	
	public int delete(int cafeno);
	
}
