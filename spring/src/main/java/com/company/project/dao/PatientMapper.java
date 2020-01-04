package com.company.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Patient;

public interface PatientMapper extends Mapper<Patient> {
	List<Patient> findAll();
	Patient findByEmail(String email);
	int addPatient(@Param("email") String email,@Param("first_name") String first_name,@Param("last_name") String last_name,@Param("gender") String gender,@Param("birthday") Date birthday,@Param("health_information") String health_information);
	int activateAccount(String email);
}