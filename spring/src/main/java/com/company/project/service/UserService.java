package com.company.project.service;
import com.company.project.model.User;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
public interface UserService extends Service<User> {
	String addUser(String email, String password, String role);
	User findByEmail(String email);
}
