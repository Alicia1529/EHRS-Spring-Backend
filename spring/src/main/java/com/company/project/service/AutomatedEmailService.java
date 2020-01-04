package com.company.project.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.dao.AppointmentMapper;
import com.company.project.model.AppointmentView;
import com.company.project.model.Patient;

@Service
@Transactional
public class AutomatedEmailService {
	@Autowired
    public JavaMailSender emailSender;
    @Autowired
    public PatientService patientService;
    @Autowired
    public AppointmentMapper appointmentMapper;
    
    public static int checkInDay = 15;
    
    public static String clientUrl = "https://im.billyzou.com/spring-ehr";

    public static String template(String reminder, String clientUrl) { 
		String template = "<!DOCTYPE html\n" + 
				"  PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"https://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
				"<html xmlns=\"https://www.w3.org/1999/xhtml\">\n" + 
				"<head>\n" + 
				"  <title>Welcome to Spring EHR!</title>\n" + 
				"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
				"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" + 
				"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \" />\n" + 
				"  <style>\n" + 
				"    .background {\n" + 
				"      padding: 30px;\n" + 
				"      width: 100%;\n" + 
				"      background-color: #34bd6e;\n" + 
				"    }\n" + 
				"    .container {\n" + 
				"      border-radius: 4px;\n" + 
				"      background: white;\n" + 
				"      width: 512px;\n" + 
				"      margin: 0 auto;\n" + 
				"      padding: 54px;\n" + 
				"      box-shadow: 0 3px 5px 0 rgba(0, 0, 0, 0.5);\n" + 
				"    }\n" + 
				"    .header {\n" + 
				"      margin: 30px 0 50px 0;\n" + 
				"      font-family: Helvetica, sans-serif;\n" + 
				"      color: #4d4d4d;\n" + 
				"      font-size: 40px;\n" + 
				"      line-height: 55px;\n" + 
				"    }\n" + 
				"    .para {\n" + 
				"      font-family: Helvetica, sans-serif;\n" + 
				"      font-size: 18px;\n" + 
				"      line-height: 29px;\n" + 
				"      color: #2b2b2b;\n" + 
				"    }\n" + 
				"    .para__btn {\n" + 
				"      background-color: #34bd6e;\n" + 
				"      padding: 16px 24px;\n" + 
				"      color: white !important;\n" + 
				"      text-decoration: none;\n" + 
				"      border-radius: 6px;\n" + 
				"    }\n" + 
				"    .para__btn:hover {\n" + 
				"      background-color: #3cd67c;\n" + 
				"    }\n" + 
				"    .para__btn:active {\n" + 
				"      background-color: #2ea35f;\n" + 
				"    }\n" + 
				"    .para__link {\n" + 
				"      color: #34bd6e !important;\n" + 
				"      font-style: italic;\n" + 
				"    }\n" + 
				"  </style>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"  <div class=\"background\">\n" + 
				"    <div class=\"container\">\n" + 
				"      <h1 class=\"header\">Spring</h1>\n" + 
				"      <p class=\"para\">\n" + 
				"        <span>Hello,</span>\n" + 
				"        <br><br>\n" + 
				"        <span>"+ reminder + "</span>\n" + 
				"        <br><br>\n" + 
				"        <span>Best,</span>\n" + 
				"        <br>\n" + 
				"        <span>Spring Team</span>\n" + 
				"        <br><br>\n" + 
				"        <a href=\""+ clientUrl + "\" class=\"para__link\">\n" + 
				"          Visit the webiste\n" + 
				"        </a>\n" + 
				"      </p>\n" + 
				"    </div>\n" + 
				"  </div>\n" + 
				"</body>\n" + 
				"</html>\n";
		return template;
}
    
    @Scheduled(cron = "0 0 10 * * ?")
	public void checkTime() {
		System.out.println("check!");
		List<Patient> patients = patientService.findAll();
		for (Patient patient : patients) {
			String email = patient.getPatientEmail();
			AppointmentView app = appointmentMapper.findLatestAppointment(email);
			if (app != null) {
				Date appDate = app.getTimeslot().getDate();
				Date currDate = new Date();
				
				LocalTime localTime = LocalTime.now();
				System.out.println(localTime);
				
				int days = (int) ((currDate.getTime() - appDate.getTime()) / (1000*3600*24));
				if (days == checkInDay) {
					this.sendCheckUpEmail(email, checkInDay);
					System.out.println("Sent check-in email to "+email);
				}			
			}
		}
	}

	public void sendCheckUpEmail(String to, int day) {
		MimeMessage message = emailSender.createMimeMessage();
		String content = String.format("It has been %d days since your last appointment. Your health is important for us! It is time for your check-up. Please visit our website to schedule an appointment today.", day);
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("ehrs.spring@gmail.com");
	        helper.setTo(to);
	        helper.setSubject("Check-Up Reminder");
	        helper.setText(template(content, clientUrl), true);
	        emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}