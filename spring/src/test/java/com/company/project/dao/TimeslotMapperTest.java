package com.company.project.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.project.model.Timeslot;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TimeslotMapperTest {
	@Autowired
	public TimeslotMapper timeslotMapper;

	@Test
	public void test() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date = formatter.parse("2019-12-08");
		List<Timeslot> time_list = timeslotMapper.findByDate("ann.wang@seimc.com", date);
		System.out.println(date);
		System.out.println("------------------");
		for (Timeslot time : time_list) {
			System.out.println(time.getStartTime());
		}
	}

}
