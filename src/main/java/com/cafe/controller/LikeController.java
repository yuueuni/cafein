package com.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.LikeDto;
import com.cafe.service.LikeService;



@CrossOrigin("*")
@RestController
@RequestMapping("/like")
public class LikeController {
	@Autowired
	private LikeService service;
	
	@GetMapping("/{cafeno}")
	public int count(@PathVariable Integer cafeno) {
		System.out.println("count likes");
		return service.count(cafeno);
	}
	
	@GetMapping("/{cafeno}/{uid}")
	public int select(@PathVariable Integer cafeno, @PathVariable String uid) {
		LikeDto like = new LikeDto();
		like.setCafeno(cafeno);
		like.setUid(uid);
		return service.selectByUser(like);
	}
	
	@PostMapping
	public String insert(@RequestBody LikeDto like) {
		System.out.println("insert like");
		if(service.insert(like)>0) {
			return "Success";
		}
		return "Failure";
	}
	
	@DeleteMapping("/{cafeno}/{uid}")
	public String delete(@PathVariable Integer cafeno, @PathVariable String uid) {
		System.out.println("delete like");
		LikeDto like = new LikeDto();
		like.setCafeno(cafeno);
		like.setUid(uid);
		if(service.delete(like)>0) {
			return "Success";
		}
		return "Failure";
	}
	
}
