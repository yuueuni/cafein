package com.web.cafe.dao;

import java.util.List;

import com.web.cafe.dto.PostDto;


public interface PostDao {
	
	public PostDto select(int pno);
	
	public List<PostDto> selectAllByUser(int page, String uid);
	
	public List<PostDto> selectAllByCafe(int page, int cafeno);
	
	public int insert(PostDto post);
	
	public int update(PostDto post);
	
	public int delete(int pno);
	
}
