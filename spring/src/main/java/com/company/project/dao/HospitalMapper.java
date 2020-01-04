package com.company.project.dao;

import java.util.List;

import com.company.project.core.Mapper;
import com.company.project.model.Hospital;

public interface HospitalMapper extends Mapper<Hospital> {
	List<Hospital> findAllHospitals();
	List<Hospital> findByHospitalKeyword(String keyword);
	Hospital findById(String email);
}