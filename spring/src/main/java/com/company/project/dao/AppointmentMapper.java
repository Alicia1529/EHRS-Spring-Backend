package com.company.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Appointment;
import com.company.project.model.AppointmentView;

public interface AppointmentMapper extends Mapper<Appointment> {
	int checkAppointment(@Param("patient_email") String patient_email,@Param("doctor_email") String doctor_email, @Param("time_slot_date") Date timeslotDate);
	int addAppointment(@Param("time_slot_id") int time_slot_id,@Param("patient_email") String patient_email,@Param("doctor_email") String doctor_email);
	int cancelAppointment(int appointment_id);
	int addCaseReport(@Param("appointment_id") int appointmentId, @Param("case_report") String caseReport);
	List<AppointmentView> findByDoctorEmail(String doctor_email);
	List<AppointmentView> findByPatientEmail(String patient_email);
	AppointmentView findLatestAppointment(String patient_email);
	AppointmentView findById(int appointment_id);
}