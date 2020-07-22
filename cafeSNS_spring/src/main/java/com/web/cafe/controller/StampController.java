package com.web.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.cafe.dto.StampDto;
import com.web.cafe.service.StampService;


@CrossOrigin("*")
@RestController
@RequestMapping("/stamp")
public class StampController {
	@Autowired
	private StampService service;
	
	@GetMapping("/{cafeno}")
	public int count(@PathVariable Integer cafeno) {
		System.out.println("count stamps");
		return service.count(cafeno);
	}
	
	@GetMapping("/{cafeno}/{uid}")
	public int select(@PathVariable Integer cafeno, @PathVariable String uid) {
		StampDto like = new StampDto();
		like.setCafeno(cafeno);
		like.setUid(uid);
		return service.selectByUser(like);
	}
	
	@PostMapping
	public String insert(@RequestBody StampDto stamp) {
		System.out.println("insert stamp");
		if(service.insert(stamp)>0) {
			return "Success";
		}
		return "Failure";
	}
	
	@DeleteMapping("/{cafeno}/{uid}")
	public String delete(@PathVariable Integer cafeno, @PathVariable String uid) {
		System.out.println("delete stamp");
		StampDto stamp = new StampDto();
		stamp.setCafeno(cafeno);
		stamp.setUid(uid);
		if(service.delete(stamp)>0) {
			return "Success";
		}
		return "Failure";
	}
	
}
