package com.company.project.dao;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.User;

public interface UserMapper extends Mapper<User> {
	int addUser(@Param("email") String email, @Param("password") String password, @Param("role") String role);
	
	User findByEmail(String email);
}