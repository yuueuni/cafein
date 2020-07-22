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

import com.cafe.dto.CommentDto;
import com.cafe.service.CommentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;



@CrossOrigin("*")
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	
	@GetMapping("/list/{pno}")
	public List<CommentDto> selectAll(@PathVariable Integer pno) {
		System.out.println("---select all comments---");
		System.out.println("PNO: " + pno);
		List<CommentDto> comments = service.selectAll(pno, 1);
		for(CommentDto c: comments) {
			System.out.println(c);
		}
		System.out.println(comments);
		return comments;
	}
	
	@PostMapping
	public int insert(@RequestBody CommentDto comment) {
		System.out.println("---insert comment---");
		System.out.println(comment);
		int cnt = service.insert(comment);
		if(cnt > 0) {
			return service.maxCno();
		}
		return -1;
	}
	
	@PutMapping
	public String update(@RequestBody CommentDto comment) {
		System.out.println("---update comment---");
		System.out.println(comment);
		int cnt = service.update(comment);
		if(cnt > 0) {
			return "Success";
		}
		return "Failure";
	}
	
	@DeleteMapping("/{cno}")
	public String delete(@PathVariable Integer cno) {
		System.out.println("---delete comment---");
		System.out.println(cno);
		int cnt = service.delete(cno);
		if(cnt > 0) {
			return "Success";
		}
		return "Failure";
	}
	
}
