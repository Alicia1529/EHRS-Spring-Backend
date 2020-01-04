package com.company.project.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.service.EmailService;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
    @Autowired
    public JavaMailSender emailSender;
    
    public static String serverUrl = "https://946f0a36.ngrok.io";
    public static String clientUrl = "https://im.billyzou.com/spring-ehr";
    public static String activationUrl = serverUrl + "/confirm_activation?token=";
    
    public static String template(String clientUrl, String activationUrl) { 
    		String template = "<!DOCTYPE html\n" + 
    		"  PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"https://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
    		"<html xmlns=\"https://www.w3.org/1999/xhtml\">\n" + 
    		"<head>\n" + 
    		"  <title>Welcome to Spring EHRS!</title>\n" + 
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
    		"        <span>Welcome to Spring EHRS. Before your\n" + 
    		"          account is created, we need to verify your email address. Please click the link below to verify your account.</span>\n" + 
    		"        <br><br>\n" + 
    		"        <a class=\"para__btn\" href=\"" + activationUrl + "\">\n" + 
    		"          Verify\n" + 
    		"        </a>\n" + 
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

	@Override
	public void sendActivationEmail(String to, String token) {
		MimeMessage message = emailSender.createMimeMessage();
        try {
        	String activationUrlToken = activationUrl+token;
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("ehrs.spring@gmail.com");
            helper.setTo(to);
            helper.setSubject("Account activation");
            helper.setText(template(clientUrl, activationUrlToken), true);

            emailSender.send(message);
            System.out.println("Activation email has been sent successfully.");
        } catch (MessagingException e) {
        	e.printStackTrace();
        }
	}
}