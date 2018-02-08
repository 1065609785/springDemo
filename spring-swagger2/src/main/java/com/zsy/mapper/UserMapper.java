package com.zsy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zsy.domain.UserInfo;

//@Mapper 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，所以统一配置@MapperScan在扫描路径在application类中
public interface UserMapper {

	@Select("SELECT * FROM user_info WHERE id = #{id}")
	@Results({ @Result(property = "userName", column = "user_Name"),
			@Result(property = "password", column = "pass_word") })
	public UserInfo get(Long id);

	@Select("SELECT * FROM user_info")
	@Results({ @Result(property = "userName", column = "user_Name"),
			@Result(property = "password", column = "pass_word") })
	public List<UserInfo> getUserList();

	@Insert("insert into user_info(user_Name, pass_word, phone,address,createTime) values(#{userName}, #{password}, #{phone},#{address},#{createTime})")
	public int add(UserInfo user);

	@Update("UPDATE user_info SET user_Name = #{user.userName} , pass_word = #{user.password}, phone = #{user.phone}, address = #{user.address}, createTime = #{user.createTime} WHERE id = #{id}")
	public int update(@Param("id") Long id, @Param("user") UserInfo user);

	@Delete("DELETE from user_info where id = #{id} ")
	public int delete(Long id);

}
