package com.company.project.service;

public interface EmailService {
	
    void sendActivationEmail(String to, String token);
    
}
