package com.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.PostDto;
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

	/* 이미지 파일 업로드 부분
	 * @Autowired private FileUploadService fuService;
	 * 
	 * @PostMapping("/upload") public String upload(@RequestParam MultipartFile
	 * image) { System.out.println("upload file"); System.out.println(image);
	 * 
	 * String url = fuService.restore(image); System.out.println("URL :" + url);
	 * return url; }
	 */
	
	@GetMapping("/{pno}")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", value = "jwt_token", required = true, dataType = "String", paramType = "header") })
	@ApiOperation(value = "게시글 조회", authorizations = { @Authorization(value="jwt_token") })
	public PostDto select(@PathVariable Integer pno) {
		System.out.println("select");
		PostDto post = service.select(pno);
		return post;
	}
	
	
	@ApiOperation(value = "유저에 따른 게시글?", authorizations = { @Authorization(value="jwt_token") })
	@GetMapping("/list/user/{page}/{uid}")
	public List<PostDto> selectAllByUser(@PathVariable Integer page, @PathVariable String uid) {
		System.out.println("list by userid");
		System.out.println(page);
		List<PostDto> posts = service.selectAllByUser(page,uid);
		return posts;
	}
	
	@ApiOperation(value = "카페에 따른 게시글?", authorizations = { @Authorization(value="jwt_token") })
	@GetMapping("/list/cafe/{page}/{cafeno}")
	public List<PostDto> selectAllByCafe(@PathVariable Integer page, @PathVariable int cafeno) {
		System.out.println("list by cafeno");
		System.out.println(page);
		List<PostDto> posts = service.selectAllByCafe(page,cafeno);
		return posts;
	}
	
	
	@ApiOperation(value = "게시글 추가", authorizations = { @Authorization(value="jwt_token") })
	@PostMapping
	public String insert(@RequestBody PostDto post) {
		System.out.println("insert");
		int cnt = service.insert(post);
		System.out.println(post);
		if(cnt > 0) {
			return "Success";
		}
		return "Failure";
	}
	
	
	@ApiOperation(value = "게시글 업데이트", authorizations = { @Authorization(value="jwt_token") })
	@PutMapping
	public String update(@RequestBody PostDto post) {
		System.out.println("update");
		System.out.println(post);
		int cnt = service.update(post);
		if(cnt > 0) {
			return "Success";
		}
		return "Failure";
	}
	
	
	@ApiOperation(value = "게시글 삭제", authorizations = { @Authorization(value="jwt_token") })
	@DeleteMapping("/{pno}")
	public String delete(@PathVariable Integer pno) {
		System.out.println("delete");
		int cnt = service.delete(pno);
		if(cnt > 0) {
			return "Success";
		}
		return "Failure";
	}
	
}
