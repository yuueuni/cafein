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

import com.cafe.dto.CafeDto;
import com.cafe.service.CafeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cafe")
//http://localhost:8080/swagger-ui.html
public class CafeController {

	@Autowired
	private CafeService service;

	// 카페검색
	@ApiOperation(value = "카페 검색해서 리스트 가져오기")
	@GetMapping("/search/{keyword}")
	public List<CafeDto> search(@PathVariable String keyword) {
		System.out.println("search cafe list");
		List<CafeDto> cafeList = service.search(keyword);
		return cafeList;
	}

	@ApiOperation(value = "카페 정보 가져오기")
	@GetMapping("/{cafeno}")
	public CafeDto select(@PathVariable Integer cafeno) {
		CafeDto cafe = service.select(cafeno);
		return cafe;
	}

	@ApiOperation(value = "카페 전체 리스트")
	@GetMapping("/list/{page}")
	public List<CafeDto> selectAll(@PathVariable Integer page) {
		System.out.println(page);
		List<CafeDto> cafeList = service.selectAll(page);
		return cafeList;
	}

	@ApiOperation(value = "카페 추가", authorizations = { @Authorization(value = "jwt_token") })
	@PostMapping
	public String insert(@RequestBody CafeDto cafe) {
		int cnt = service.insert(cafe);
		System.out.println(cafe);
		if (cnt > 0) {
			return "Success";
		}
		return "Failure";
	}

	@ApiOperation(value = "카페 수정", authorizations = { @Authorization(value = "jwt_token") })
	@PutMapping
	public String update(@RequestBody CafeDto cafe) {
		System.out.println("update");
		System.out.println(cafe);
		int cnt = service.update(cafe);
		if (cnt > 0) {
			return "Success";
		}
		return "Failure";
	}

	@ApiOperation(value = "카페 삭제", authorizations = { @Authorization(value = "jwt_token") })
	@DeleteMapping("/delete/{cafeno}")
	public String delete(@PathVariable Integer cafeno) {
		System.out.println("delete");
		int cnt = service.delete(cafeno);
		if (cnt > 0) {
			return "Success";
		}
		return "Failure";
	}

}
