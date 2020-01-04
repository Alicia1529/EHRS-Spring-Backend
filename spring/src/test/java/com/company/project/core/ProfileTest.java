package com.company.project.core;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.project.dao.AppointmentMapper;
import com.company.project.model.User;
import com.company.project.role.Role;
import com.company.project.service.UserService;
import com.company.project.util.JWTUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProfileTest {

	@Autowired
    private UserService userService;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Test
	public void test() {
    	String email = "patientE@nyu.edu";
    	String role = "Patient";

    	try {
    		Role personalRole = (Role) Class.forName("com.company.project.role."+role+"Role").newInstance();
			Profile profile = new Profile(personalRole);
			System.out.println(profile.getPersonalInfo(email));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
	}

}
