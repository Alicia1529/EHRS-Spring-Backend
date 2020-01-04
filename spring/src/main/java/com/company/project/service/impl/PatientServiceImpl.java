package com.company.project.service.impl;

import com.company.project.dao.PatientMapper;
import com.company.project.model.Patient;
import com.company.project.service.PatientService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
@Service
@Transactional
public class PatientServiceImpl extends AbstractService<Patient> implements PatientService {
    @Resource
    private PatientMapper patientMapper;
    
    @Override
    public Patient findByEmail(String email) {
    	return patientMapper.findByEmail(email);
    }

    @Override
    public int addPatient(String email,String first_name, String last_name, String gender, Date birthday, String health_information) {
    	return patientMapper.addPatient(email, first_name, last_name, gender, birthday, health_information);
    }

	@Override
	public int activateAccount(String email) {
		return patientMapper.activateAccount(email);
	}
	
	@Override
	public List<Patient> findAll(){
		return patientMapper.findAll();
	}	
	
    
}
