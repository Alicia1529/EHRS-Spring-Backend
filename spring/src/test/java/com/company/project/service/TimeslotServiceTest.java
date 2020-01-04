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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TimeslotServiceTest {
	
	@Autowired
	private TimeslotService timeslotService;

	@Test
	public void testFindByDoctorEmail() {
		List<String> timeslots = timeslotService.findByDoctorEmail("Cathy.Ali@seimc.com");
		Assert.assertNotNull(timeslots);
	}

	@Test
	@Transactional
	public void testAddTimeSlot() throws ParseException {
		Assert.assertEquals(1, timeslotService.addTimeSlot("Cathy.Ali@seimc.com", 7, "2020-12-25", "09:00", 5));
	}

}
