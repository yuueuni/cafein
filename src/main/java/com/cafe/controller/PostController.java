package com.cafe.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cafe.dto.CafeDto;
import com.cafe.dto.PostDto;
import com.cafe.service.CafeService;
import com.cafe.service.FileUploadService;
import com.cafe.service.PostService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService service;

	@Autowired
	private CafeService caService;
	
	@Autowired
	private FileUploadService fuService;

	
	@PostMapping("/upload")
	public String upload(@RequestParam MultipartFile image) {
		System.out.println("upload file");
		System.out.println(image); 

		String url = fuService.restore(image);
		System.out.println("URL :" + url);
		return url;
	}
	
	@ApiOperation(value="post 이미지 가져오기 ")
	@GetMapping(value="/get/image/{pno}",
			produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable Integer pno) {
		String imgPath = "/home/data/images/";
		PostDto post = service.select(pno);
		String target = imgPath+post.getImage();
		System.out.println("post image : " + target);
		FileInputStream in;
		try {
			in = new FileInputStream(target);
			return IOUtils.toByteArray(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("i cant find file");
			e1.printStackTrace();
			return null;
		}
	}
	
	

	@GetMapping("/{pno}")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", value = "jwt_token", required = true, dataType = "String", paramType = "header") })
	@ApiOperation(value = "게시글 조회")
	public PostDto select(@PathVariable Integer pno) {
		System.out.println("select post");

		PostDto post = service.select(pno);
		CafeDto cafe = caService.select(post.getCafeno());
		post.setCafename(cafe.getName());
		System.out.println(post);
		return post;
	}

	@ApiOperation(value = "게시글 전체 리스트")
	@GetMapping("/list/{page}")
	public List<PostDto> selectAll(@PathVariable Integer page) {
		System.out.println(page);
		List<PostDto> postList = service.selectAll(page);
		return postList;
	}
	
	@ApiOperation(value = "유저가 작성한 게시글 수")
	@GetMapping("/count/{uid}")
	public int count(@PathVariable String uid) {
		System.out.println("count post");
		return service.countByUser(uid);
	}

	@ApiOperation(value = "유저에 따른 게시글 리스트")
	@GetMapping("/list/user/{page}/{uid}")
	public List<PostDto> selectAllByUser(@PathVariable Integer page, @PathVariable String uid) {
		System.out.println("list by userid");
		System.out.println(page);
		List<PostDto> posts = service.selectAllByUser(page, uid);
		
		for(PostDto p : posts) {
			CafeDto cafe = caService.select(p.getCafeno());
			p.setCafename(cafe.getName());	
		}
		return posts;
	}

	@ApiOperation(value = "카페에 따른 게시글 리스트")
	@GetMapping("/list/cafe/{page}/{cafeno}")
	public List<PostDto> selectAllByCafe(@PathVariable Integer page, @PathVariable int cafeno) {
		System.out.println("list by cafeno");
		System.out.println(page);
		List<PostDto> posts = service.selectAllByCafe(page, cafeno);
		for(PostDto p : posts) {
			CafeDto cafe = caService.select(p.getCafeno());
			p.setCafename(cafe.getName());	
		}
		return posts;
	}

	@CrossOrigin
	@ApiOperation(value = "게시글 작성", authorizations = { @Authorization(value = "jwt_token") })
	@PostMapping
	public String insert(@RequestBody PostDto post) {
		System.out.println("게시글 작성");
	
		int cnt = service.insert(post);
		System.out.println(post);
		if (cnt > 0) { 
			return "Success";
		}
		return "Failure";
	}

	@ApiOperation(value = "게시글 수정", authorizations = { @Authorization(value = "jwt_token") })
	@PutMapping
	public String update(@RequestBody PostDto post) {
		System.out.println("update");
		System.out.println(post);
		int cnt = service.update(post);
		if (cnt > 0) {
			return "Success";
		}
		return "Failure";
	}

	@ApiOperation(value = "게시글 삭제", authorizations = { @Authorization(value = "jwt_token") })
	@DeleteMapping("/delete/{pno}")
	public String delete(@PathVariable Integer pno) {
		System.out.println("delete");
		int cnt = service.delete(pno);
		if (cnt > 0) {
			return "Success";
		}
		return "Failure";
	}

}
