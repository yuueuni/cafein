package com.web.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.cafe.dao.PostDao;
import com.web.cafe.dto.PostDto;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao dao;
	
	@Override
	public PostDto select(int pno) {
		return dao.select(pno);
	}

	@Override
	public List<PostDto> selectAllByUser(int page, String uid) {
		return dao.selectAllByUser(page,uid);
	}

	@Override
	public List<PostDto> selectAllByCafe(int page, int cafeno) {
		return dao.selectAllByCafe(page,cafeno);
	}
	
	@Override
	public int insert(PostDto post) {
		return dao.insert(post);
	}

	@Override
	public int update(PostDto post) {
		return dao.update(post);
	}

	@Override
	public int delete(int pno) {
		return dao.delete(pno);
	}

}
