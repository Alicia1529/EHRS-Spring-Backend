package com.company.project.service.impl;

import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.core.AbstractService;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
	
	private int hashIterations = 2;
	
    @Resource
    private UserMapper userMapper;
    
    @Override
    public String addUser(String email, String password, String role) {
    	SimpleHash hashedPassword = new SimpleHash("md5",password, ByteSource.Util.bytes(email),hashIterations);
    	String secret = hashedPassword.toHex();
    	userMapper.addUser(email, secret, role);
    	return secret;
    }
    
    @Override
    public User findByEmail(String email) {
    	return userMapper.findByEmail(email);
    }
}
