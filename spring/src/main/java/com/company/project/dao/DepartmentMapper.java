package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Department;

public interface DepartmentMapper extends Mapper<Department> {
	List<Department> findByHospitalId(String hospital_id);
	int addDepartment(@Param("hospital_id") String hospital_id,@Param("department_name") String department_name);
}