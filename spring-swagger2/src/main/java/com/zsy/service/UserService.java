package com.zsy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsy.domain.UserInfo;

public interface UserService {
	public UserInfo get(Long id);

	public List<UserInfo> getUserList();

	public int add(UserInfo user);

	public int update(@Param("id") Long id, @Param("user") UserInfo user);

	public int delete(Long id);
}
