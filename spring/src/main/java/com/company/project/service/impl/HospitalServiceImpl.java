package com.company.project.service.impl;

import com.company.project.dao.HospitalMapper;
import com.company.project.model.Hospital;
import com.company.project.service.HospitalService;
import com.alibaba.fastjson.JSON;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
@Service
@Transactional
public class HospitalServiceImpl extends AbstractService<Hospital> implements HospitalService {
    @Resource
    private HospitalMapper hospitalMapper;
    
    @Override
    public List<String> findAllHospitals() {
    	List<Hospital> hospitals = hospitalMapper.findAllHospitals();
    	Iterator iterator = hospitals.iterator();
    	List<String> jsonString = new ArrayList();
    	
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}
    	
    	return jsonString;
    }
    
    @Override
    public List<String> findByHospitalKeyword(String keyword) {
    	String newKeyword = "%"+keyword+"%";
    	
    	List<Hospital> hospitals = hospitalMapper.findByHospitalKeyword(newKeyword);
    	Iterator iterator = hospitals.iterator();
    	List<String> jsonString = new ArrayList();
    
    	
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}
    	
    	return jsonString;
    }

	@Override
	public Hospital findById(String email) {
		return hospitalMapper.findById(email);
	}
    
}
