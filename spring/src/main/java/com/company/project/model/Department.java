package com.company.project.model;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class Department {
    @Id
    @Column(name = "department_id")
    @JSONField(name="departmentId")
    private Integer departmentId;

    @Column(name = "hospital_id")
    @JSONField(name="hospitalId")
    private String hospitalId;

    @Column(name = "department_name")
    @JSONField(name="departmentName")
    private String departmentName;

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
     * @return department_name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}