package com.zsy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zsy.domain.UserInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/demo")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParams({ @ApiImplicitParam(dataType = "UserInfo", name = "userInfo", value = "用户信息", required = true) })
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody UserInfo userInfo) {
		logger.info("保存用户信息：" + JSON.toJSONString(userInfo));
		String body = "保存成功";
		ResponseEntity<String> entity = new ResponseEntity<String>(body, HttpStatus.OK);
		return entity;
	}

	@ApiOperation(value = "获取用户", notes = "通过id获取用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(dataType = "Long", name = "id", value = "用户id", required = true, paramType = "path") })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserInfo> get(@PathVariable Long id) {
		logger.info("用户id：" + id);
		UserInfo info = new UserInfo();
		info.setAddress("安徽省合肥市蜀山区");
		info.setId(id);
		info.setPassword("********");
		info.setPhone("15755365206");
		info.setUserName("zhaoshouyun");
		ResponseEntity<UserInfo> entity = new ResponseEntity<UserInfo>(info, HttpStatus.OK);
		return entity;
	}

	@ApiOperation(value = "更新用户", notes = "全量更新")
	@ApiImplicitParams({ @ApiImplicitParam(dataType = "UserInfo", name = "userInfo", value = "用户信息", required = true) })
	@RequestMapping(value = "/user/update", method = RequestMethod.PUT)
	public ResponseEntity<UserInfo> update(@RequestBody UserInfo userInfo) {
		logger.info("用户更新信息：" + JSON.toJSONString(userInfo));
		userInfo.setUserName("更新名称：" + userInfo.getUserName());
		ResponseEntity<UserInfo> entity = new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
		return entity;
	}

	@ApiOperation(value = "删除用户", notes = "通过id删除用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(dataType = "Long", name = "id", value = "用户id", required = true, paramType = "path") })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		logger.info("删除用户id：" + id);
		ResponseEntity<String> entity = new ResponseEntity<String>("删除成功", HttpStatus.OK);
		return entity;
	}
}
