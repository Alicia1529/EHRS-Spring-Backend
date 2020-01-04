package com.company.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Timeslot;

public interface TimeslotMapper extends Mapper<Timeslot> {
	List<Timeslot> findByDoctorEmail(@Param("doctor_email") String doctor_email,@Param("now") Date date);
	List<Timeslot> findByDate(@Param("doctor_email") String doctor_email, @Param("date") Date date);
	Timeslot findById(int time_slot_id);
	int addTimeSlot(@Param("doctor_email") String doctor_email,@Param("seat") int seat,@Param("date") Date date,@Param("start_time") String start_time);
	int updateTimeSlot(int time_slot_id);
	int addTimeSlotSeats(int time_slot_id);
}