package com.company.project.role;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.company.project.model.Doctor;
import com.company.project.model.DoctorView;
import com.company.project.service.DoctorService;

@Component
public class DoctorRole implements Role{
	@Resource
	private DoctorService doctorService;
	
	private static DoctorRole doctorRole;
	
	@PostConstruct
	public void init() {
		doctorRole = this;
	}

	@Override
	public String getRoleInfo(String email) {
		DoctorView doctor = doctorRole.doctorService.findByEmail(email);
    	return JSON.toJSONString(doctor);
	}

}
