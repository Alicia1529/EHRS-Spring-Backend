package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @Id
    @Column(name = "user_email")
    @JSONField(name="user_email")
    private String userEmail;
    
    @JSONField(name="password")
    private String password;
    
    @JSONField(name="role")
    private String role;

    /**
     * @return user_email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}