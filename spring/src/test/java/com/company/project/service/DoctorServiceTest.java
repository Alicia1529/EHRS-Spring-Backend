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

import com.company.project.model.Doctor;
import com.company.project.model.DoctorView;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DoctorServiceTest {
	
	@Autowired
	private DoctorService doctorService;

	@Test
	public void testFindByEmail() {
		DoctorView doctor = doctorService.findByEmail("Cathy.Ali@seimc.com");
		Assert.assertEquals("Cathy", doctor.getFirstName());
		Assert.assertEquals("Ali", doctor.getLastName());
		System.out.println(doctor.getHospital().getHospitalName());
	}
	
	@Test
	public void testFindByHospitalId() {
		List<String> doctor = doctorService.findByHospitalId("seimc1228");
	}

	@Test
	@Transactional
	public void testAddDoctor() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date = formatter.parse("2019-10-25");
		Assert.assertEquals(1, doctorService.addDoctor("test2@nyu.edu", 1, "seimc1228", "Internship Doctor", date, "female", "a", "a"));
	}

}
