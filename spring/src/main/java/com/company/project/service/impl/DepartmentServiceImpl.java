package com.company.project.service.impl;

import com.company.project.dao.DepartmentMapper;
import com.company.project.model.Department;
import com.company.project.service.DepartmentService;
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
public class DepartmentServiceImpl extends AbstractService<Department> implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<String> findByHospitalId(String hospital_id) {
    	List<Department> departments = departmentMapper.findByHospitalId(hospital_id);
    	Iterator iterator = departments.iterator();
    	List<String> jsonString = new ArrayList();
    	
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}
    	
    	return jsonString;
    }

	@Override
	public int addDepartment(String hospital_id, String department_name) {
		return departmentMapper.addDepartment(hospital_id, department_name);
	}
}
