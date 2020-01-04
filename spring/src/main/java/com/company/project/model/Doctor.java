package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class Doctor {
    @Id
    @Column(name = "doctor_email")
    @JSONField(name="doctorEmail")
    private String doctorEmail;

    @Column(name = "department_id")
    @JSONField(name="departmentId")
    private Integer departmentId;

    @Column(name = "hospital_id")
    @JSONField(name="hospitalId")
    private String hospitalId;
    
    @JSONField(name="title")
    private String title;
    
    @JSONField(name="birthday")
    private Date birthday;
    
    @Column(name = "dr_gender")
    @JSONField(name="gender")
    private String gender;

    @Column(name = "dr_first_name")
    @JSONField(name="firstName")
    private String firstName;

    @Column(name = "dr_last_name")
    @JSONField(name="lastName")
    private String lastName;

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

    /**
     * @return department_id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return hospital_id
     */
    public String getHospitalId() {
        return hospitalId;
    }

    /**
     * @param hospitalId
     */
    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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
}