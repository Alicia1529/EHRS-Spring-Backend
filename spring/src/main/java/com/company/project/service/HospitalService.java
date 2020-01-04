package com.company.project.service;
import com.company.project.model.Hospital;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
public interface HospitalService extends Service<Hospital> {
	List<String> findAllHospitals();
	List<String> findByHospitalKeyword(String keyword);
	Hospital findById(String email);
}
