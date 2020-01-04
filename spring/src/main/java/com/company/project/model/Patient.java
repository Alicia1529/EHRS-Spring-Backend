package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class Patient {
    @Id
    @Column(name = "patient_email")
    @JSONField(name="patientEmail")
    private String patientEmail;

    @Column(name = "first_name")
    @JSONField(name="firstName")
    private String firstName;

    @Column(name = "last_name")
    @JSONField(name="lastName")
    private String lastName;

    @JSONField(name="gender")
    private String gender;
    
    @JSONField(name="birthday")
    private Date birthday;
 
    @JSONField(name="status")
    private int status;

	@Column(name = "health_information")
    @JSONField(name="healthInformation")
    private String healthInformation;
    

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
     * @return first_name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return last_name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return health_information
     */
    public String getHealthInformation() {
        return healthInformation;
    }

    /**
     * @param healthInformation
     */
    public void setHealthInformation(String healthInformation) {
        this.healthInformation = healthInformation;
    }

}