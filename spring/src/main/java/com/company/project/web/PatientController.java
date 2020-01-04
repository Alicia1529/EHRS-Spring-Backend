package com.company.project.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Context;
import com.company.project.dao.AppointmentMapper;
import com.company.project.model.AppointmentView;
import com.company.project.model.Patient;
import com.company.project.model.User;
import com.company.project.service.AppointmentService;
import com.company.project.service.EmailService;
import com.company.project.service.PatientService;
import com.company.project.util.JWTUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
* Created by CodeGenerator on 2019/09/21.
*/
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Resource
    private PatientService patientService;
    @Resource
    private AppointmentService appointmentService;
    @Resource
    private AppointmentMapper appointmentMapper;
    @Resource 
    private EmailService emailService;
    
    private Context context = new Context();
    
    @PostMapping("/information")
    @RequiresRoles("PATIENT")
    public JSONObject information(@RequestHeader("Authorization") String token,
    		@RequestParam("firstName") String firstName,
    		@RequestParam("lastName") String lastName,
    		@RequestParam("gender") String gender,
    		@RequestParam("birthday") String birthday,
    		@RequestParam("healthInformation") String healthInformation) throws ParseException {
    	
    	String email = JWTUtil.getEmail(token);
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date birthdayDate = formatter.parse(birthday);
    	
    	patientService.addPatient(email, firstName, lastName, gender, birthdayDate, healthInformation);
		emailService.sendActivationEmail(email, token);
		return context.handleRequest200(true, "Activation email has been sent and Information completed", null);
    	
    }
    
    @PostMapping("/appointment")
    @RequiresRoles("PATIENT")
    public JSONObject makeAppointment(@RequestHeader("Authorization") String token,
    		@RequestParam("timeSlotId") int timeSlotId,
    		@RequestParam("doctorEmail") String doctorEmail) {
    	String email = JWTUtil.getEmail(token);
    	int success = appointmentService.addAppointment(timeSlotId, email, doctorEmail);
    	if (success == 0) {
    		return context.handleRequest200(false, "You already have an appointment. Please check again.", null);
    	}
    	return context.handleRequest200(true, "Appointment successfully made.", null);
    }
    
    @GetMapping("/appointment")
    @RequiresRoles("PATIENT")
    public JSONObject appointment(@RequestHeader("Authorization") String token) {
    	String email = JWTUtil.getEmail(token);
    	List<String> appointments = appointmentService.findByPatientEmail(email);
    	return context.handleRequest200(true, null, appointments.toString());
    }
    
    @PostMapping("/cancel_appointment")
    @RequiresRoles("PATIENT")
    public JSONObject cancelAppointment(@RequestHeader("Authorization") String token,
    		@RequestParam("timeSlotId") int timeSlotId,
    		@RequestParam("appointmentId") int appointmentId) {
    	int success = appointmentService.cancelAppointment(appointmentId, timeSlotId);
    	if (success == 1) {
    		return context.handleRequest200(true, "Appointment successfully cancelled.", null);
    	} else {
    		return context.handleRequest200(false, "Unable to cancel the appointment.", null);
    	}
    }
    
    @GetMapping("/get_patient_daily_analysis")
    @RequiresRoles("PATIENT")
    public JSONObject dailyAnalysis(@RequestHeader("Authorization") String token) {
    	String email = JWTUtil.getEmail(token);
		AppointmentView app = appointmentMapper.findLatestAppointment(email);
		String analysis;
		if (app == null) {
			analysis = "Glad to help you with your heath problem."
					+ " If you have any questions, please feel free to call us.";
		}
		else {
			Date appDate = app.getTimeslot().getDate();
			Date currDate = new Date();
			int days = (int) ((appDate.getTime() - currDate.getTime()) / (1000*3600*24));
			if (days == 0) {
				analysis = "Kind Reminder: You have an appointment today at "
						+ app.getTimeslot().getStartTime()
						+ " in " + app.getHospital().getHospitalName();
			}
			else if (days > 0) {
				DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");  
				analysis = "Kind Reminder: You have an incoming appointment on "
						+ dateFormat.format(appDate) + " at "
						+ app.getHospital().getHospitalName();
			}
			else if (days > -30){
				analysis = "Hope you feel better after your last appointment : )";
				
			}
			else {
				analysis = "It has been " + (-days) + " days since your last appointment. "
						+ "Do you want to make another appointment?";				
			}
		}
		
    	return context.handleRequest200(true, null, analysis);
    }

    
}
