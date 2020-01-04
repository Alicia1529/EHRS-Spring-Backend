package com.company.project.service.impl;

import com.company.project.dao.DoctorMapper;
import com.company.project.model.Doctor;
import com.company.project.model.DoctorView;
import com.company.project.model.Hospital;
import com.company.project.service.DoctorService;
import com.alibaba.fastjson.JSON;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
@Service
@Transactional
public class DoctorServiceImpl extends AbstractService<Doctor> implements DoctorService {
    
	@Resource
    private DoctorMapper doctorMapper;

	@Override
	public List<String> findByHospitalId(String hospital_id) {
		List<Doctor> doctors = doctorMapper.findByHospitalId(hospital_id);
    	Iterator iterator = doctors.iterator();
    	List<String> jsonString = new ArrayList();
    	
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}
    	
    	return jsonString;
	}
	
	@Override
	public List<String> findByDepartmentName(String department_name) {
		List<Doctor> doctors = doctorMapper.findByDepartmentName(department_name);
    	Iterator iterator = doctors.iterator();
    	List<String> jsonString = new ArrayList();
    	
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}
    	
    	return jsonString;
	}

	@Override
	public List<String> findByDepartmentId(int department_id) {
		List<Doctor> doctors = doctorMapper.findByDepartmentId(department_id);
    	Iterator iterator = doctors.iterator();
    	List<String> jsonString = new ArrayList();
    	
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}
    	
    	return jsonString;
	}

	@Override
	public List<String> findByName(String name) {
		String[] inputs = name.split(" ");
		String first_name = inputs[0];
		List<Doctor> doctors;
		if (inputs.length > 1) {
			String last_name = inputs[1];
			doctors = doctorMapper.findByFullName(first_name, last_name);
		} else {
			doctors = doctorMapper.findBySingleName(first_name);
		}
		
		Iterator iterator = doctors.iterator();
    	List<String> jsonString = new ArrayList();
    	
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}
    	
    	return jsonString;
	}
	
	@Override
	public DoctorView findByEmail(String doctor_email) {
		return doctorMapper.findByEmail(doctor_email);
	}
	
	@Override
	public int addDoctor(String doctor_email, int department_id, String hospital_id, String title, Date birthday,
			String gender, String first_name, String last_name) {
		return doctorMapper.addDoctor(doctor_email, department_id, hospital_id, title, birthday, gender, first_name, last_name);
	}


}
