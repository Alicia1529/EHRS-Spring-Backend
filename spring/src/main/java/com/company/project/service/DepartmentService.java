package com.company.project.service;
import com.company.project.model.Department;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
public interface DepartmentService extends Service<Department> {
	List<String> findByHospitalId(String hospital_id);
	int addDepartment(String hospital_id, String department_name);
}
