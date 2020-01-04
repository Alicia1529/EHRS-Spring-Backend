package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class Timeslot {
    @Id
    @Column(name = "time_slot_id")
    @JSONField(name="timeSlotId")
    private Integer timeSlotId;

    @Column(name = "doctor_email")
    @JSONField(name="doctorEmail")
    private String doctorEmail;

    @Column(name = "seat")
    @JSONField(name="seat")
    private Integer seat;
    
    @Column(name = "capacity")
    @JSONField(name="capacity")
    private Integer capacity;

    @Column(name = "date")
    @JSONField(name="date")
    private Date date;

    @Column(name = "start_time")
    @JSONField(name="startTime")
    private String startTime;


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
     * @return seat
     */
    public Integer getSeat() {
        return seat;
    }

    /**
     * @param seat
     */
    public void setSeat(Integer seat) {
        this.seat = seat;
    }
    
    /**
     * @return capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * @param capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return start_time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}