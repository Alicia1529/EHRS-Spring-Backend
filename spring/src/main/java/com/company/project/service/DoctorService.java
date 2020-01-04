package com.company.project.service;
import com.company.project.model.Doctor;
import com.company.project.model.DoctorView;

import java.util.Date;
import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
public interface DoctorService extends Service<Doctor> {
	List<String> findByHospitalId(String hospital_id);
	List<String> findByDepartmentName(String department_name);
	List<String> findByDepartmentId(int department_id);
	List<String> findByName(String name);
	DoctorView findByEmail(String email);
	int addDoctor(String doctor_email,int department_id,String hospital_id,String title,
			Date birthday,String gender,String first_name,String last_name);
}
