package com.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.dto.UserDto;
import com.mysql.cj.Session;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SqlSession ssesion;
	
	@Override
	public List<UserDto> search(String keyword) {
		return ssesion.selectList("user.search", keyword);
	}
	
	@Override
	public int insert(UserDto user) {
		return ssesion.insert("user.insert", user);
	}

	@Override
	public int delete(String id) {
		return ssesion.delete("user.delete", id);
	}

	@Override
	public int update(UserDto user) {
		return ssesion.update("user.update", user);
	}

	@Override
	public UserDto select(String id) {
		return ssesion.selectOne("user.select", id);
	}

	@Override
	public List<UserDto> selectAll() {
		return ssesion.selectList("user.selectAll");
	}

	@Override
	public UserDto findpassword(UserDto user) {
		return ssesion.selectOne("user.findpassword", user);
	}

	

}
