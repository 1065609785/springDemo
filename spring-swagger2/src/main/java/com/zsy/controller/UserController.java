package com.zsy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zsy.domain.UserInfo;
import com.zsy.service.UserService;
import com.zsy.vo.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	private final static String OK = "ok";

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParams({ @ApiImplicitParam(dataType = "UserInfo", name = "userInfo", value = "用户信息", required = true) })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> save(@RequestBody UserInfo userInfo) {
		logger.info("保存用户信息：" + JSON.toJSONString(userInfo));
		// ResponseEntity<String> entity = new ResponseEntity<String>(body,
		// HttpStatus.OK);
		JsonResult result = new JsonResult();
		int i = userService.add(userInfo);
		result.setResult(i);
		result.setStatus(OK);
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "获取用户", notes = "通过id获取用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(dataType = "Long", name = "id", value = "用户id", required = true, paramType = "path") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> get(@PathVariable Long id) {
		logger.info("用户id：" + id);
		UserInfo userInfo = userService.get(id);
		return ResponseEntity.ok(new JsonResult(OK, userInfo));
	}

	@ApiOperation(value = "获取所有的用户", notes = "获取所有的用户信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> get() {
		List<UserInfo> list = userService.getUserList();
		return ResponseEntity.ok(new JsonResult(OK, list));
	}

	@ApiOperation(value = "更新用户", notes = "全量更新")
	@ApiImplicitParams({ @ApiImplicitParam(dataType = "UserInfo", name = "userInfo", value = "用户信息", required = true),
			@ApiImplicitParam(dataType = "java.lang.Long", name = "id", value = "用户id", required = true, paramType = "path") })
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JsonResult> update(@PathVariable Long id, @RequestBody UserInfo userInfo) {
		logger.info("用户更新信息：" + JSON.toJSONString(userInfo));
		int i = userService.update(id, userInfo);
		return ResponseEntity.ok(new JsonResult(OK, i));
	}

	@ApiOperation(value = "删除用户", notes = "通过id删除用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(dataType = "Long", name = "id", value = "用户id", required = true, paramType = "path") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JsonResult> delete(@PathVariable Long id) {
		logger.info("删除用户id：" + id);
		int i = userService.delete(id);
		return ResponseEntity.ok(new JsonResult(OK, i));
	}
}
