package com.zsy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsy.domain.UserInfo;
import com.zsy.mapper.UserMapper;
import com.zsy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public UserInfo get(Long id) {
		return userMapper.get(id);
	}

	@Override
	public List<UserInfo> getUserList() {
		return userMapper.getUserList();
	}

	@Override
	public int add(UserInfo user) {
		return userMapper.add(user);
	}

	@Override
	public int update(Long id, UserInfo user) {
		return userMapper.update(id, user);
	}

	@Override
	public int delete(Long id) {
		return userMapper.delete(id);
	}

}
