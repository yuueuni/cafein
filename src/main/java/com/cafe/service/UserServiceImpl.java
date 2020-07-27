package com.cafe.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dao.UserDao;
import com.cafe.dto.UserDto;


@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDao getUserDao() {
		return userDao;
	}

	@Override
	public List<String> search(String keyword) {
		return userDao.search(keyword);
	}
	@Override
	public UserDto select(String id) {
		return userDao.select(id);
	}

	@Override
	public UserDto login(String id, String pw) {
		UserDto user = userDao.select(id);
		if(user != null && user.getPassword().equals(pw)) {
			return user;
		}else {
			return null;
		}
	}

	@Override
	public int join(UserDto user) {
		int result = userDao.insert(user);
		UserDto selected = userDao.select(user.getId());
		logger.trace("조회 결과:{}", selected);
		return result;
	}

	@Override
	public int delete(String id) {
		return userDao.delete(id);
	}

	@Override
	public int update(UserDto user) {
		return userDao.update(user);
	}

	@Override
	public List<UserDto> selectAll() {
		return userDao.selectAll();
	}

	@Override
	public UserDto findpassword(UserDto user) {
		UserDto result = userDao.findpassword(user);
		if(result != null && result.getPassword() != null) {
			return result;
		}else {
			return null;
		}
	}

	

}
