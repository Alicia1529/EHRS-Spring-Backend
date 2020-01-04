package com.company.project.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.project.model.Hospital;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HospitalServiceTest {
	
	@Autowired
	private HospitalService hospitalService;

	@Test
	public void testFindAllHospitals() {
		List<String> hospitals = hospitalService.findAllHospitals();
		Assert.assertNotNull(hospitals);
	}

	@Test
	public void testFindById() {
		Hospital hospital = hospitalService.findById("seimc1228");
		Assert.assertNotNull(hospital);
	}

}
