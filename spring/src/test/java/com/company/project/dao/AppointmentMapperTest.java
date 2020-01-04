package com.company.project.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppointmentMapperTest {
	@Autowired
	public AppointmentMapper appointmentMapper;

	@Test
	public void test() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date timeslotDate = formatter.parse("2019-10-27");
		int success = appointmentMapper.checkAppointment("bi@nyu.edu", "Cathy.Ali@seimc.com", timeslotDate);
		System.out.println(success);
	}

}
