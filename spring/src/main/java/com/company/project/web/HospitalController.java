package com.company.project.web;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Context;
import com.company.project.service.DepartmentService;
import com.company.project.service.DoctorService;
import com.company.project.service.HospitalService;
import com.company.project.service.UserService;
import com.company.project.util.JWTUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
* Created by CodeGenerator on 2019/09/21.
*/
@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Resource
    private HospitalService hospitalService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private UserService userService;
    
    private Context context = new Context();
    
    @PostMapping("/department")
    @RequiresRoles("HOSPITAL")
    public JSONObject addDepartment(@RequestHeader("Authorization") String token,
    		@RequestParam("departmentName") String departmentName) {
    	String hospitalId = JWTUtil.getEmail(token);
    	departmentService.addDepartment(hospitalId, departmentName);
    	return context.handleRequest200(true, "New department successfully added.", null);
    }
    
    @PostMapping("/doctor")
    @RequiresRoles("HOSPITAL")
    public JSONObject addDoctor(@RequestHeader("Authorization") String token, @RequestParam("password") String password,
    		@RequestParam("doctorEmail") String doctorEmail,@RequestParam("departmentId") String departmentId,
    		@RequestParam("title") String title,@RequestParam("birthday") String birthday,
    		@RequestParam("gender") String gender,@RequestParam("firstName") String firstName,
    		@Param("lastName") String lastName) throws ParseException {
    	String hospitalId = JWTUtil.getEmail(token);
    	System.out.println(hospitalId);
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date birthdayDate = formatter.parse(birthday);
        
        int parsedId = Integer.parseInt(departmentId);
    	
        userService.addUser(doctorEmail, password, "DOCTOR");
    	doctorService.addDoctor(doctorEmail, parsedId, hospitalId, title, birthdayDate, gender, firstName, lastName);
    	return context.handleRequest200(true, "New doctor successfully added.", null);
    }
    
    @GetMapping("/list")
    public JSONObject list() {
    	List<String> hospitals = hospitalService.findAllHospitals();
    	return context.handleRequest200(true, null, hospitals.toString());
    }
    
    @GetMapping("/department")
    public JSONObject department(@RequestParam("hospitalId") String hospitalId) {
    	List<String> departments = departmentService.findByHospitalId(hospitalId);
    	return context.handleRequest200(true, null, departments.toString());
    }
    
    @GetMapping("/doctor")
    public JSONObject doctor(@RequestParam("hospitalId") String hospitalId) {
    	List<String> doctors = doctorService.findByHospitalId(hospitalId);
    	return context.handleRequest200(true, null, doctors.toString());
    }   
}
