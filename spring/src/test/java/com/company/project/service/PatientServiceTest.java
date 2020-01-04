package com.company.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.company.project.model.Patient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PatientServiceTest {
	
	@Autowired
	private PatientService patientService;

	@Test
	public void testFindAll() {
		List<Patient> patients = patientService.findAll();
		System.out.println(JSON.toJSONString(patients));
	}

	@Test
	public void testFindByEmail() {
		Patient patient = patientService.findByEmail("patientE@nyu.edu");
		System.out.println("patient:"+JSON.toJSONString(patient));
		Assert.assertNotNull(patient);
	}

}
