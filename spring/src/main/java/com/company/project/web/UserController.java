package com.company.project.web;
import com.company.project.core.Context;
import com.company.project.core.Profile;
import com.company.project.model.Doctor;
import com.company.project.model.Hospital;
import com.company.project.model.Patient;
import com.company.project.model.User;
import com.company.project.role.Role;
import com.company.project.service.DoctorService;
import com.company.project.service.EmailService;
import com.company.project.service.HospitalService;
import com.company.project.service.PatientService;
import com.company.project.service.UserService;
import com.company.project.status.Status;
import com.company.project.util.JWTUtil;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
* Created by CodeGenerator on 2019/09/21.
*/
@RestController
@RequestMapping("/")
public class UserController {
	
	private int hashIterations = 2;
	
    @Resource
    private UserService userService;
    @Resource
    private PatientService patientService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private HospitalService hospitalService;
    @Resource
    private EmailService emailService;
    
    private Context context = new Context();
    
    private static String clientUrl = "https://im.billyzou.com/spring-ehr";
    
    @PostMapping("/register")
    public JSONObject register(@RequestParam("email") String email,
    					   @RequestParam("password") String password,
    					   @RequestParam("role") String role) {
    	System.out.println(email);
    	User user = userService.findByEmail(email);
    	if (user != null) {
    		return context.handleRequest(10);
    	} else {
    		String secret = userService.addUser(email, password, role);
    		
    		JSONObject jsonObject = new JSONObject();
    		jsonObject.put("token", JWTUtil.sign(email, secret));
    		jsonObject.put("role",role);
    		return context.handleRequest200(true, "Registered successfully.", jsonObject.toJSONString());
    	}
    }
    
    @PostMapping("/login")
    public JSONObject login(@RequestParam("email") String email,
    					@RequestParam("password") String password) {

    	User user = userService.findByEmail(email);
    	
    	SimpleHash hashedPassword = new SimpleHash("md5",password, ByteSource.Util.bytes(email),hashIterations);
    	String secret = hashedPassword.toHex();
    	
    	String result = new String();
    	if (user == null) {
    		return context.handleRequest(11);
    	}
    	else if (user.getPassword().equals(secret)) {
    		String role = user.getRole();
    		if (role.equals("PATIENT")) {
    			Patient patient = patientService.findByEmail(email);
    			System.out.println(role + patient.getStatus() + (patient.getStatus() == 0));
    			if (patient.getStatus() == 0) {
    		    	String token = JWTUtil.sign(email, secret);
            		emailService.sendActivationEmail(email, token);
            		return context.handleRequest200(false, "Activation email has been sent successfully.", null);		
    			}
    		}
    		JSONObject jsonObject = new JSONObject();
    		jsonObject.put("token", JWTUtil.sign(email, secret));
    		jsonObject.put("role",role);
    		return context.handleRequest200(true, "Login successfully.", jsonObject.toJSONString());
    	}
    	else {
    		return context.handleRequest(11);
    	}
    }
    
    @GetMapping("/information")
    public JSONObject information(@RequestHeader("Authorization") String token) {
    	String email = JWTUtil.getEmail(token);
    	
    	User user = userService.findByEmail(email);
    	String role = caseConverter(user.getRole());

    	try {
    		Role personalRole = (Role) Class.forName("com.company.project.role."+role+"Role").newInstance();
			Profile profile = new Profile(personalRole);
			return context.handleRequest200(true, null, profile.getPersonalInfo(email));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
    	return null;   	
    }
    
    private String caseConverter(String role) {
    	return role.substring(0,1)+role.substring(1).toLowerCase();
    }
    
    
    @GetMapping("/confirm_activation")
    public void confirmActivation(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
    	String email = JWTUtil.getEmail(token);
    	System.out.println("Confirm Activation"+ email + "token:"+token);
    	patientService.activateAccount(email);
    	response.sendRedirect(clientUrl);

//    	return "redirect:"+ clientUrl;
    }
    
    
}
