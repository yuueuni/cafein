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
import com.cafe.service.CafeService;
import com.cafe.service.RecommendService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

	@Autowired
	private RecommendService service;
	
	@Autowired
	private CafeService caService;
	
	@ApiOperation(value = "좋아요 기반 추천 리스트")
	@GetMapping("/like/{uid}")
	public List<CafeDto> recommendByLike(@PathVariable String uid){
		List<Integer> myCafeList = service.selectCafeLiked(uid);//내가 좋아요 누른 카페들
		List<String> userList = new ArrayList<>();//내가 좋아요 누른 카페들을 좋아요 누른 유저들
		List<Integer> othersCafeList = new ArrayList<>();//다른 유저들이 좋아요 누른 카페들
		List<CafeDto> recommendList = new ArrayList<>();//추천할 카페들
		
		for(Integer cafeno :myCafeList) {//userList 구하기
			List<String> tmpList = service.selectUserLiked(cafeno);
			for(String now: tmpList) {
				if(!now.equals(uid) && !userList.contains(now)) {
					userList.add(now);
				}
			}
		}
		for(String user:userList) {//othersCafeList 구하기
			List<Integer> tmpList = service.selectCafeLiked(user);
			for(Integer cafeno: tmpList) {
				if(!myCafeList.contains(cafeno) && !othersCafeList.contains(cafeno)) {
					othersCafeList.add(cafeno);
					recommendList.add(caService.select(cafeno));
				}
			}
		}
		Collections.sort(recommendList, new Comparator<CafeDto>() {

			@Override
			public int compare(CafeDto o1, CafeDto o2) {
				return -Integer.compare(o1.getLike_count(), o2.getLike_count());
			}
		});
				
		return recommendList;
	}
	
	@ApiOperation(value = "스탬프 기반 추천 리스트")
	@GetMapping("/stamp/{uid}")
	public List<CafeDto> recommendByStamp(@PathVariable String uid){
		List<Integer> myCafeList = service.selectCafeStamped(uid);//내가 좋아요 누른 카페들
		List<String> userList = new ArrayList<>();//내가 좋아요 누른 카페들을 좋아요 누른 유저들
		List<Integer> othersCafeList = new ArrayList<>();//다른 유저들이 좋아요 누른 카페들
		List<CafeDto> recommendList = new ArrayList<>();//추천할 카페들
		
		for(Integer cafeno :myCafeList) {//userList 구하기
			List<String> tmpList = service.selectUserStamped(cafeno);
			for(String now: tmpList) {
				if(!now.equals(uid) && !userList.contains(now)) {
					userList.add(now);
				}
			}
		}
		for(String user:userList) {//othersCafeList 구하기
			List<Integer> tmpList = service.selectCafeStamped(user);
			for(Integer cafeno: tmpList) {
				if(!myCafeList.contains(cafeno) && !othersCafeList.contains(cafeno)) {
					othersCafeList.add(cafeno);
					recommendList.add(caService.select(cafeno));
				}
			}
		}
				
		return recommendList;
	}

}
