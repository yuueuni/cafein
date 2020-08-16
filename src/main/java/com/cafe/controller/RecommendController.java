package com.cafe.controller;

import java.util.*;
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

import com.cafe.dto.CafeDto;
import com.cafe.service.RecommendService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

	@Autowired
	private RecommendService service;
	
	@ApiOperation(value = "좋아요 기반 추천 리스트")
	@GetMapping("/like/{uid}")
	public List<CafeDto> recommendByLike(@PathVariable String uid){
		List<CafeDto> myCafeList = service.selectCafeLiked(uid);//내가 좋아요 누른 카페들
		List<String> userList = new ArrayList<>();//내가 좋아요 누른 카페들을 좋아요 누른 유저들
		//List<CafeDto> othersCafeList = new ArrayList<>();//다른 유저들이 좋아요 누른 카페들
		List<CafeDto> recommendList = new ArrayList<>();//추천할 카페들
		for(CafeDto cafe:myCafeList) {//userList 구하기
			List<String> tmpList = service.selectUserLiked(cafe.getCafeno());
			for(String now: tmpList) {
				if(!now.equals(uid) && !userList.contains(now)) {
					userList.add(now);
				}
			}
		}
		for(String user:userList) {//recommendList 구하기
			List<CafeDto> tmpList = service.selectCafeLiked(user);
			for(CafeDto cafe: tmpList) {
				if(!myCafeList.contains(cafe) && !recommendList.contains(cafe)) {
					recommendList.add(cafe);
				}
			}
		}
				
		return recommendList;
	}

}
