package com.company.project.service;
import com.company.project.model.Patient;

import java.util.Date;
import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
public interface PatientService extends Service<Patient> {
	List<Patient> findAll();
	Patient findByEmail(String email);
	int addPatient(String email,String first_name, String last_name, String gender, Date birthday, String health_information);
	int activateAccount(String email);
}
