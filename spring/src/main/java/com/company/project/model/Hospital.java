package com.company.project.model;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class Hospital {
    @Id
    @Column(name = "hospital_id")
    @JSONField(name="hospitalId")
    private String hospitalId;

    @Column(name = "hospital_name")
    @JSONField(name="hospitalName")
    private String hospitalName;

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
     * @return hospital_name
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * @param hospitalName
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}