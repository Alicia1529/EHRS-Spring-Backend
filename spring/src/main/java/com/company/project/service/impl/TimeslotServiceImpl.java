package com.company.project.service.impl;

import com.company.project.dao.TimeslotMapper;
import com.company.project.model.Timeslot;
import com.company.project.service.TimeslotService;
import com.alibaba.fastjson.JSON;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/09/21.
 */
@Service
@Transactional
public class TimeslotServiceImpl extends AbstractService<Timeslot> implements TimeslotService {
    @Resource
    private TimeslotMapper timeslotMapper;

	@Override
	public List<String> findByDoctorEmail(String doctor_email) {
		Date date = new Date();
		List<Timeslot> doctors = timeslotMapper.findByDoctorEmail(doctor_email, date);
    	Iterator<Timeslot> iterator = doctors.iterator();
    	List<String> jsonString = new ArrayList<String>();
    	
    	while (iterator.hasNext()) {
    		jsonString.add(JSON.toJSONString(iterator.next()));
    	}
    	
    	return jsonString;
	}

	@Override
	public int addTimeSlot(String doctor_email, int seat, String date, String start_time, int num) throws ParseException {
		
		String updated_time = start_time;
		List<String> time_list = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			time_list.add(updated_time);
			updated_time = updated_time.concat(":00");
			java.sql.Time myTime = java.sql.Time.valueOf(updated_time);
			
			LocalTime localtime = myTime.toLocalTime();
			localtime = localtime.plusMinutes(30);
			updated_time = localtime.toString();
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date fdate = formatter.parse(date);
        
        List<Timeslot> forbidden_time_list = timeslotMapper.findByDate(doctor_email, fdate);
        
        for (Timeslot f : forbidden_time_list) {
        	if (time_list.contains(f.getStartTime())) {
        		return 0;
        	}
        }
		
		int remainder = seat%num;
		int base_seat = seat/num;
		for (int i = 0; i < num; i++) {
			String time = time_list.get(i);
			if (i < remainder) {
				timeslotMapper.addTimeSlot(doctor_email, base_seat+1, fdate, time);
			}
			else {
				timeslotMapper.addTimeSlot(doctor_email, base_seat, fdate, time);
			}
		}
		
		return 1;
	}

}
