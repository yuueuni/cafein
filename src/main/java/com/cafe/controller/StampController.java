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

import com.cafe.dto.StampDto;
import com.cafe.service.StampService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;



@CrossOrigin("*")
@RestController
@RequestMapping("/stamp")
public class StampController {
	@Autowired
	private StampService service;
	
	@ApiOperation(value = "발도장 수")
	@GetMapping("/{cafeno}")
	public int count(@PathVariable Integer cafeno) {
		System.out.println("count stamps");
		return service.count(cafeno);
	}
	
	@ApiOperation(value = "이전에 발도장 눌렀는지 체크(눌렀으면 1,안눌렀으면 0)", authorizations = { @Authorization(value="jwt_token") })
	@GetMapping("/check/{cafeno}/{uid}")
	public int select(@PathVariable Integer cafeno, @PathVariable String uid) {
		StampDto like = new StampDto();
		like.setCafeno(cafeno);
		like.setUid(uid);
		return service.selectByUser(like);
	}
	
	@ApiOperation(value = "발도장 추가", authorizations = { @Authorization(value="jwt_token") })
	@PostMapping
	public String insert(@RequestBody StampDto stamp) {
		System.out.println("insert stamp");
		if(service.insert(stamp)>0) {
			return "Success";
		}
		return "Failure";
	}
	
	@ApiOperation(value = "발도장 삭제", authorizations = { @Authorization(value="jwt_token") })
	@DeleteMapping("/delete/{cafeno}/{uid}")
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
