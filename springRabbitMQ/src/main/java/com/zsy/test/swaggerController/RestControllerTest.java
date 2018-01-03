
 /**
 * Project Name:springRabbitMQ
 * File Name:RestController.java
 * Package Name:com.zsy.test.controller
 * Date:2017年12月28日上午9:51:04
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
*/

package com.zsy.test.swaggerController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ClassName: RestController 
 * Function: TODO ADD FUNCTION. 
 * date: 2017年12月28日 上午9:51:12 
 * @author zhaoshouyun
 * 
 * http://localhost:8085/springRabbitMQ/dist/index.html
 * @version 
 * @since JDK 1.7
 */
//本controller的功能描述
@Api(value = "Test", description = "test the swagger API")
@RestController("/rest")
public class RestControllerTest {
	@ApiOperation(value = "save add", notes = "保存方法", response = String.class,httpMethod="post")
	@RequestMapping(value="/add",method = RequestMethod.POST , produces = {"application/json; charset=UTF-8"})
	public ResponseEntity<String> add(@RequestBody @ApiParam(value="保存一个user",required=true) String name){
		System.err.println("新增方法，参数name= "+name);
		return new ResponseEntity<String>(name+",hello-------add", HttpStatus.OK); 
	}
	@ApiOperation(value = "update the test", notes = "update the test", response = String.class)
	@RequestMapping(value="/update",method = RequestMethod.PUT , produces = {"application/json; charset=UTF-8"})
	public ResponseEntity<String> put(String name){
		System.err.println("修改方法，参数name= "+name);
		return new ResponseEntity<String>( name+",hello-------put", HttpStatus.OK) ;
	}
	
	@ApiOperation(value = "delete the  test", notes = "delete the test by the test id", response = String.class)
	@RequestMapping(value="/delete",method = RequestMethod.DELETE , produces = {"application/json; charset=UTF-8"})
	public ResponseEntity<String> DELETE(String id){
		System.err.println("删除方法，参数id= "+id);
		return new ResponseEntity<String>( id+",delete --", HttpStatus.OK) ;
	}
	
	@ApiOperation(value = "get the test", notes = "get the test by the test id", response = String.class)
	@RequestMapping(value="/get",method = RequestMethod.GET , produces = {"application/json; charset=UTF-8"})
	public ResponseEntity<String> get(String id){
		System.err.println("获取方法，参数id= "+id);
		return new ResponseEntity<String>(id+",get --", HttpStatus.OK) ; 
	}
}

