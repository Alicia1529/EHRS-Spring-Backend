package com.company.project.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.company.project.model.AppointmentView;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppointmentViewMapperTest {
	@Autowired
	public AppointmentMapper appointmentMapper;
	
	@Test
	public void test() {
		
		AppointmentView app1 = appointmentMapper.findLatestAppointment("aqangelaluo@gmail.com");
		Date date1 = app1.getTimeslot().getDate(); 
		Date date2 = new Date();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(date2);
		System.out.println(date);
		
		
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		System.out.println(days);
		
		List<AppointmentView> apps = appointmentMapper.findByPatientEmail("patientE@nyu.edu");
		for (AppointmentView app : apps) {
			System.out.println(JSON.toJSONString(app));
		}
		
		List<AppointmentView> app = appointmentMapper.findByDoctorEmail("Cathy.Ali@seimc.com");
		for (AppointmentView appv : app) {
			System.out.println(JSON.toJSONString(appv));
		}
	}

}
