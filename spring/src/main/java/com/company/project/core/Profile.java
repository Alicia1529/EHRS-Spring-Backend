package com.company.project.core;

import com.company.project.role.Role;
import com.company.project.status.Status;
import com.company.project.status.Status200;

/**
 * 响应结果生成工具
 */
public class Profile {
    private Role role;
    
    public Profile(Role role) {
    	this.role = role;
    }
    
    public String getPersonalInfo(String email) {
    	return role.getRoleInfo(email);
    }
    
}
