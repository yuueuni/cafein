package com.cafe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe.dto.CafeDto;
import com.cafe.dto.LoginUserDto;
import com.cafe.dto.UserDto;
import com.cafe.service.UserService;
import com.cafe.service.jwt.JwtService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


//Ajax요청의 응답 전용 컨트롤러!!
@CrossOrigin("*")
@RestController 
@RequestMapping("/api/user")
public class UserController {
   
   
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);
   private static final String OK = "SUCCESS";
   private static final String FAIL = "FAIL";
   
   
   @Autowired
   UserService userservice;
   
   @Autowired
   private JwtService jwtService;
   
   
   
   @ApiOperation(value = "id에 맞는 유저을 조회합니다.", response = List.class)
   @GetMapping("/{id}")
   public ResponseEntity<UserDto> select(Model m, @PathVariable("id") String id) {
      return new ResponseEntity<>(userservice.select(id), HttpStatus.OK);
   }// 해당 user조회 : http://localhost:8080/api/user/select/{id} + GET
   
   
   //유저검색
   @GetMapping("/search/{keyword}")
   public List<String> search(@PathVariable String keyword) {
		System.out.println("search user list");
		List<String> userList = userservice.search(keyword);
		return userList;
	}
   //로그인 구현
   @PostMapping("/login")
   @ApiOperation(value="로그인을 하기 위해 id와 pw를 보낸다.", response = Integer.class)
   public ResponseEntity login(@RequestBody LoginUserDto loginUser) {
      String id = loginUser.getId();
      String pw = loginUser.getPw();
      UserDto loginuser = userservice.login(id, pw);
      if(loginuser == null) {
         System.out.println("로그인 실패");
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      
      String token = jwtService.create("user", loginuser, "user"); // 토큰 발급 유저 정보 들어있음
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("Authorization", token);
      
      return new ResponseEntity<String>(token, HttpStatus.OK);
   }
   
   //회원가입
   @PostMapping("/signup")
   @ApiOperation(value="회원가입", response = Integer.class)
   public ResponseEntity signup(@RequestBody UserDto user) {
      
      // 여기 중복 체크
      int result = userservice.join(user);
      if(result == -1) { // 등록 안되면
         System.out.println(result);
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 에러 
      }
      String token = jwtService.create("user", user, "user"); // 토큰 발급 유저 정보 들어있음
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("Authorization", token);
      return new ResponseEntity<String>(token,HttpStatus.OK); // 성공시 ,
   }
   
   //회원탈퇴
   @DeleteMapping("/delete/{id}")
   @ApiOperation(value="회원탈퇴", response = Integer.class)
   public ResponseEntity delete(@PathVariable("id") String id) {
      int result = userservice.delete(id);
      if(result < 0) {
         return new ResponseEntity<String>(FAIL,HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<String>(OK, HttpStatus.OK);
   }
   
   @PutMapping("/update")
   @ApiOperation(value="회원정보수정", response = Integer.class)
   public ResponseEntity update(@RequestBody UserDto user) {
      int result = userservice.update(user);
      if(result == -1) { // 등록 안되면
         return new ResponseEntity<String>(FAIL,HttpStatus.BAD_REQUEST); // 400 에러 
      }
      return new ResponseEntity<String>(OK,HttpStatus.OK); // 성공시 ,
   }
      
}