package com.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.FollowDto;
import com.cafe.service.FollowService;



@CrossOrigin("*")
@RestController
@RequestMapping("/follow")
public class FollowController {
	@Autowired
	private FollowService service;
	
	@GetMapping("/count/follower/{followingid}")
	public int countFollower(@PathVariable String followingid) {
		System.out.println("count follower");
		return service.countFollower(followingid);
	}
	@GetMapping("/count/following/{uid}")
	public int countFollowing(@PathVariable String uid) {
		System.out.println("count following");
		return service.countFollowing(uid);
	}
	
	@GetMapping("/list/follower/{followingid}")
	public List<String> selectFollower(@PathVariable String followingid) {
		System.out.println("select follower");
		System.out.println("followingid: " + followingid);
		List<String> follwerList = service.selectFollower(followingid);
		for(String s: follwerList) {
			System.out.println(s);
		}
		return follwerList;
	}
	
	@GetMapping("/list/following/{uid}")
	public List<String> selectFollowing(@PathVariable String uid) {
		System.out.println("select following");
		System.out.println("uid: " + uid);
		List<String> follwingList = service.selectFollowing(uid);
		for(String s: follwingList) {
			System.out.println(s);
		}
		return follwingList;
	}
	@PostMapping
	public String insert(@RequestBody FollowDto follow) {
		System.out.println("insert follow");
		if(service.insert(follow)>0) {
			return "Success";
		}
		return "Failure";
	}
	
	@DeleteMapping("/{uid}/{followingid}")
	public String delete(@PathVariable String uid, @PathVariable String followingid) {
		System.out.println("delete follow");
		FollowDto follow = new FollowDto();
		follow.setUid(uid);;
		follow.setFollowingid(followingid);;
		if(service.delete(follow)>0) {
			return "Success";
		}
		return "Failure";
	}
	
}
