package com.company.project.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppointmentServiceTest {
	
	@Autowired
	private AppointmentService appointmentService;

	@Test
	public void test() {
		appointmentService.cancelAppointment(15, 68);
//		appointmentService.addCaseReport(3,"{}");
	}

}
