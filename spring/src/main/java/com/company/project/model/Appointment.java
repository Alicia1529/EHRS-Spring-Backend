package com.company.project.model;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class Appointment {
    @Id
    @Column(name = "appointment_id")
    @JSONField(name="appointmentId")
    private Integer appointment_id;

    @Column(name = "time_slot_id")
    @JSONField(name="timeSlotId")
    private Integer time_slot_id;

    @Column(name = "patient_email")
    @JSONField(name="patientEmail")
    private String patient_email;

    @Column(name = "doctor_email")
    @JSONField(name="doctorEmail")
    private String doctor_email;

    @Column(name = "case_description")
    @JSONField(name="caseDescription")
    private String caseDescription;
    
    public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	/**
     * @return appointment_id
     */
    public Integer getappointment_id() {
        return appointment_id;
    }

    /**
     * @param appointment_id
     */
    public void setappointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
    }

    /**
     * @return time_slot_id
     */
    public Integer gettime_slot_id() {
        return time_slot_id;
    }

    /**
     * @param time_slot_id
     */
    public void settime_slot_id(Integer time_slot_id) {
        this.time_slot_id = time_slot_id;
    }

    /**
     * @return patient_email
     */
    public String getpatient_email() {
        return patient_email;
    }

    /**
     * @param patient_email
     */
    public void setpatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    /**
     * @return doctor_email
     */
    public String getdoctor_email() {
        return doctor_email;
    }

    /**
     * @param doctor_email
     */
    public void setdoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }
}