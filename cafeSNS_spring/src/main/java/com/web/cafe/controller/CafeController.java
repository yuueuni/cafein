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

import com.web.cafe.dto.CafeDto;
import com.web.cafe.service.CafeService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin("*")
@RestController
@RequestMapping("/cafe")
//http://localhost:8080/swagger-ui.html
public class CafeController {

	
	@Autowired
	private CafeService service;

	
	
	@GetMapping("/{cafeno}")
	@ApiOperation(value = "카페정보")
	public CafeDto select(@PathVariable Integer cafeno) {
		System.out.println("select");
		CafeDto cafe = service.select(cafeno);
		return cafe;
	}
	
	@GetMapping("/list/{page}")
	@ApiOperation(value = "전체리스트")
	public List<CafeDto> selectAll(@PathVariable Integer page) {
		System.out.println("list");
		System.out.println(page);
		List<CafeDto> cafeList = service.selectAll(page);
		return cafeList;
	}
	
	@PostMapping
	@ApiOperation(value = "추가")
	public String insert(@RequestBody CafeDto cafe) {
		System.out.println("insert");
		int cnt = service.insert(cafe);
		System.out.println(cafe);
		if(cnt > 0) {
			return "Success";
		}
		return "Failure";
	}
	
	@PutMapping
	@ApiOperation(value = "수정")
	public String update(@RequestBody CafeDto cafe) {
		System.out.println("update");
		System.out.println(cafe);
		int cnt = service.update(cafe);
		if(cnt > 0) {
			return "Success";
		}
		return "Failure";
	}
	
	@DeleteMapping("/{cafeno}")
	@ApiOperation(value = "삭제")
	public String delete(@PathVariable Integer cafeno) {
		System.out.println("delete");
		int cnt = service.delete(cafeno);
		if(cnt > 0) {
			return "Success";
		}
		return "Failure";
	}
	
}
