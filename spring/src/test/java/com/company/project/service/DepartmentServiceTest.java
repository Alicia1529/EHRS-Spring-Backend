package com.company.project.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DepartmentServiceTest {
	
	@Autowired
	private DepartmentService departmentService;

	@Test
	public void testFindByHospitalId() {
		List<String> department = departmentService.findByHospitalId("seimc1228");
		Assert.assertNotNull(department);
	}

	@Test
	@Transactional
	public void testAddDepartment() {
		Assert.assertEquals(1, departmentService.addDepartment("seimc1228", "Department of Dermatology"));
	}

}

