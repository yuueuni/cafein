package com.web.cafe.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.cafe.dto.PostDto;
import com.web.cafe.service.PostService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin("*")
@RestController
@RequestMapping("/post")
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
	@ApiOperation(value = "게시글 조회")
	public PostDto select(@PathVariable Integer pno) {
		System.out.println("select");
		PostDto post = service.select(pno);
		return post;
	}
	
	@GetMapping("/list/user/{page}/{uid}")
	public List<PostDto> selectAllByUser(@PathVariable Integer page, @PathVariable String uid) {
		System.out.println("list by userid");
		System.out.println(page);
		List<PostDto> posts = service.selectAllByUser(page,uid);
		return posts;
	}
	
	@GetMapping("/list/cafe/{page}/{cafeno}")
	public List<PostDto> selectAllByCafe(@PathVariable Integer page, @PathVariable int cafeno) {
		System.out.println("list by cafeno");
		System.out.println(page);
		List<PostDto> posts = service.selectAllByCafe(page,cafeno);
		return posts;
	}
	
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
