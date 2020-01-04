package com.company.project.service;
import com.company.project.model.Appointment;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
public interface AppointmentService extends Service<Appointment> {
	int addAppointment(int time_slot_id, String patient_email, String doctor_email);
	int cancelAppointment(int appointment_id, int time_slot_id);
	int addCaseReport(int appointmentId, String caseReport);
	List<String> findByPatientEmail(String patient_email);
	List<String> findByDoctorEmail(String doctor_email);
}