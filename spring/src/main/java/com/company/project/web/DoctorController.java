package com.company.project.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Context;
import com.company.project.model.Doctor;
import com.company.project.model.DoctorView;
import com.company.project.service.AppointmentService;
import com.company.project.service.DoctorService;
import com.company.project.service.TimeslotService;
import com.company.project.util.JWTUtil;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/09/21.
*/
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
    private DoctorService doctorService;
    @Resource
    private TimeslotService timeslotService;
    @Resource
    private AppointmentService appointmentService;

    private Context context = new Context();
 
    @GetMapping("/information")
    public JSONObject information(@RequestParam("doctorEmail") String doctorEmail) {
    	DoctorView doctor = doctorService.findByEmail(doctorEmail);
    	if (doctor == null) {
    		return context.handleRequest(13);
    	}
    	return context.handleRequest200(true, null, JSON.toJSONString(doctor));
    }
    
    @GetMapping("/appointment")
    public JSONObject appointment(@RequestHeader("Authorization") String token) {
    	String email = JWTUtil.getEmail(token);
    	List<String> appointments = appointmentService.findByDoctorEmail(email);
    	return context.handleRequest200(true, null, appointments.toString());
    }
    
    @GetMapping("/appointment/patient")
    @RequiresRoles("DOCTOR")
    public JSONObject patient_appointment(@RequestParam("patientEmail") String patientEmail) {
    	System.out.println("doctor/appointment/patient"+patientEmail);
    	List<String> appointments = appointmentService.findByPatientEmail(patientEmail);
    	return context.handleRequest200(true, null, appointments.toString());
    }
    
    @GetMapping("/search")
    public JSONObject search(@RequestParam("departmentId") int departmentId) {
    	List<String> doctors = doctorService.findByDepartmentId(departmentId);
    	return context.handleRequest200(true, null, doctors.toString());
    }
    
    @PostMapping("/case_report")
    @RequiresRoles("DOCTOR")
    public JSONObject addCaseReport(@RequestParam("appointmentId") int appointmentId,
    		@RequestParam("caseDescription") String caseReport) {
    	appointmentService.addCaseReport(appointmentId, caseReport);
    	return context.handleRequest200(true, "Successfully add patient's case report", null);
    }
    
}
