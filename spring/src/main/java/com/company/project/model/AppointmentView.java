package com.company.project.model;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class AppointmentView {
	@Id
    @Column(name = "appointment_id")
    @JSONField(name="appointmentId")
    private Integer appointmentId;

    @Column(name = "time_slot_id")
    @JSONField(name="timeSlotId")
    private Integer timeSlotId;

    @Column(name = "patient_email")
    @JSONField(name="patientEmail")
    private String patientEmail;

    @Column(name = "doctor_email")
    @JSONField(name="doctorEmail")
    private String doctorEmail;
 
    @Column(name = "case_description")
    @JSONField(name="caseDescription")
    private String caseDescription;
    
	@Column(name = "doctor")
    private Doctor doctor;
    
    @Column(name = "department")
    private Department department;
    
    @Column(name = "hospital")
    private Hospital hospital;
    
    @Column(name = "timeslot")
    private Timeslot timeslot;
    
    @Column(name = "patient")
    private Patient patient;   
   
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
    	this.doctor = doctor;
    }

    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
    	this.department = department;
    }

    public Hospital getHospital() {
        return hospital;
    }
    
    public void setHospital(Hospital hospital) {
    	this.hospital = hospital;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }
    
    public void setTimeslot(Timeslot timeslot) {
    	this.timeslot = timeslot;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

    public void setPatient(Patient patient) {
    	this.patient = patient;
    }
    /**
     * @return appointment_id
     */
    public Integer getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId
     */
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return time_slot_id
     */
    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    /**
     * @param timeSlotId
     */
    public void setTimeSlotId(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    /**
     * @return patient_email
     */
    public String getPatientEmail() {
        return patientEmail;
    }

    /**
     * @param patientEmail
     */
    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    /**
     * @return doctor_email
     */
    public String getDoctorEmail() {
        return doctorEmail;
    }

    /**
     * @param doctorEmail
     */
    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }
}