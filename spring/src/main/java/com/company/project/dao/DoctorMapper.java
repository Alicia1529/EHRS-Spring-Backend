package com.company.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Doctor;
import com.company.project.model.DoctorView;

public interface DoctorMapper extends Mapper<Doctor> {
	List<Doctor> findByHospitalId(String hospital_id);
	List<Doctor> findByDepartmentName(String department_name);
	List<Doctor> findByDepartmentId(int department_id);
	List<Doctor> findByFullName(@Param("first_name") String first_name, @Param("last_name") String last_name);
	List<Doctor> findBySingleName(String first_name);
	DoctorView findByEmail(String doctor_email);
	int addDoctor(@Param("doctor_email") String doctor_email,@Param("department_id") int department_id,
			@Param("hospital_id") String hospital_id,@Param("title") String title,
			@Param("birthday") Date birthday,@Param("gender") String gender,
			@Param("first_name") String first_name,@Param("last_name") String last_name);
}