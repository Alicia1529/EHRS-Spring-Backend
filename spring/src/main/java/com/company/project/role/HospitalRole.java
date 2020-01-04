package com.company.project.role;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.company.project.model.Doctor;
import com.company.project.model.Hospital;
import com.company.project.service.DoctorService;
import com.company.project.service.HospitalService;

@Component
public class HospitalRole implements Role{
	@Resource
	private HospitalService hospitalService;
	
	private static HospitalRole hospitalRole;
	
	@PostConstruct
	public void init() {
		hospitalRole = this;
	}

	@Override
	public String getRoleInfo(String email) {
		Hospital hospital = hospitalRole.hospitalService.findById(email);
    	return JSON.toJSONString(hospital);
	}

}
