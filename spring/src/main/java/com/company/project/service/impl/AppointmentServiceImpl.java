package com.company.project.service.impl;

import com.company.project.dao.AppointmentMapper;
import com.company.project.dao.TimeslotMapper;
import com.company.project.model.Appointment;
import com.company.project.model.AppointmentView;
import com.company.project.service.AppointmentService;
import com.alibaba.fastjson.JSON;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
@Service
@Transactional
public class AppointmentServiceImpl extends AbstractService<Appointment> implements AppointmentService {
    @Resource
    private AppointmentMapper appointmentMapper;
    @Resource
    private TimeslotMapper timeslotMapper;

	@Override
	public int addAppointment(int time_slot_id, String patient_email, String doctor_email) {
		Date timeslotDate = timeslotMapper.findById(time_slot_id).getDate();
		int numAppointment = appointmentMapper.checkAppointment(patient_email, doctor_email, timeslotDate);
		if (numAppointment == 0) {
			timeslotMapper.updateTimeSlot(time_slot_id);
			return appointmentMapper.addAppointment(time_slot_id, patient_email, doctor_email);			
		}
		return 0;

	}

	@Override
	public List<String> findByPatientEmail(String patient_email) {
		List<AppointmentView> appointmentInfo = appointmentMapper.findByPatientEmail(patient_email);
		Iterator<AppointmentView> iterator = appointmentInfo.iterator();
    	List<String> jsonString = new ArrayList<String>();
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}	
    	return jsonString;
	}
	
	@Override
	public List<String> findByDoctorEmail(String doctor_email){
		List<AppointmentView> appointmentInfo = appointmentMapper.findByDoctorEmail(doctor_email);
		Iterator<AppointmentView> iterator = appointmentInfo.iterator();
    	List<String> jsonString = new ArrayList<String>();
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}	
    	return jsonString;
	}
	
	@Override
	public int cancelAppointment(int appointment_id, int time_slot_id) {
		System.out.println("cancelAppointment"+appointment_id);
		AppointmentView app = appointmentMapper.findById(appointment_id);
		if (app == null) {
			return 0;
		}
		Date appDate = app.getTimeslot().getDate();
		Date currDate = new Date();
		
		int days = (int) ((appDate.getTime() - currDate.getTime()) / (1000*3600*24));
		if (days <= 1) { 
			return 0; 
		}
		appointmentMapper.cancelAppointment(appointment_id);	
		timeslotMapper.addTimeSlotSeats(time_slot_id);
		return 1;
	}

	@Override
	public int addCaseReport(int appointmentId, String caseReport) {
		appointmentMapper.addCaseReport(appointmentId, caseReport);
		return 1;
	}
}
