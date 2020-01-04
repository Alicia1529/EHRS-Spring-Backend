package com.company.project.role;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.company.project.model.Patient;
import com.company.project.service.PatientService;

@Component
public class PatientRole implements Role{
	@Autowired
	private PatientService patientService;
	
	private static PatientRole patientRole;
	
	@PostConstruct
	public void init() {
		patientRole = this;
	}

	@Override
	public String getRoleInfo(String email) {
		Patient patient = patientRole.patientService.findByEmail(email);
    	return JSON.toJSONString(patient);
	}

}
