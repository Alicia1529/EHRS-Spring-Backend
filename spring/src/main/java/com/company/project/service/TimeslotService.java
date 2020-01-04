package com.company.project.service;
import com.company.project.model.Timeslot;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
public interface TimeslotService extends Service<Timeslot> {
	List<String> findByDoctorEmail(String doctor_email);
	int addTimeSlot(String doctor_email, int seat, String date, String start_time, int num) throws ParseException;
}
