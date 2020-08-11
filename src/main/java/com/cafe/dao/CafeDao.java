package com.cafe.dao;

import java.util.List;

import com.cafe.dto.CafeDto;
import com.cafe.dto.GeoDto;


public interface CafeDao {
	
	public List<CafeDto> search(String keyword);
	
	public CafeDto select(int cafeno);
	
	public List<CafeDto> selectAll(int page);
	public List<CafeDto> selectAllAll(GeoDto geo); // this is used for finding geo
	
	public int insert(CafeDto cafe);
	
	public int update(CafeDto cafe);
	
	public int delete(int cafeno);
	
}
