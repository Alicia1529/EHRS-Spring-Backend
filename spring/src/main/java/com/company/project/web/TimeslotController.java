package com.company.project.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Context;
import com.company.project.model.Timeslot;
import com.company.project.service.TimeslotService;
import com.company.project.util.JWTUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
* Created by CodeGenerator on 2019/09/21.
*/
@RestController
@RequestMapping("/timeslot")
public class TimeslotController {
    @Resource
    private TimeslotService timeslotService;
    
    private Context context = new Context();

    @PostMapping("/post")
    @RequiresRoles("DOCTOR")
    public JSONObject addTimeSlot(@RequestHeader("Authorization") String token, 
    		@RequestParam("seat") int seat, @RequestParam("date") String date,
    		@RequestParam("startTime") String startTime,
    		@RequestParam("numTimeSlot") int num) throws ParseException {
    	
    	String doctor_email = JWTUtil.getEmail(token);      
		
    	int success = timeslotService.addTimeSlot(doctor_email, seat, date, startTime, num);
    	if (success == 1) {
    		return context.handleRequest200(true, "New time slot successfully added.", null);
    	} else {
    		return context.handleRequest200(false, "There exists conflict with current time slots.", null);
    	}
    }

    @GetMapping("/view")
    public JSONObject viewTimeSlot(@RequestParam(value="doctorEmail", required=true) String doctorEmail) {
    	List<String> timeslots = timeslotService.findByDoctorEmail(doctorEmail);
    	return context.handleRequest200(true, null, timeslots.toString());
    }
    
}
