package com.cafe.service;

import java.util.List;

import com.cafe.dto.PostDto;


public interface PostService {

	public PostDto select(int pno);

	public List<PostDto> selectAllByUser(int page, String uid);

	public List<PostDto> selectAllByCafe(int page, int cafeno);

	public int insert(PostDto post);

	public int update(PostDto post);

	public int delete(int pno);

}