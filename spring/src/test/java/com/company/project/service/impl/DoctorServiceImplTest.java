package com.company.project.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.project.service.DoctorService;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DoctorServiceImplTest {


	@Autowired
	private DoctorService doctorService;
	
	@Test
	public void test() {
		doctorService.findByEmail("Cathy.Ali@seimc.com");
	}

}
