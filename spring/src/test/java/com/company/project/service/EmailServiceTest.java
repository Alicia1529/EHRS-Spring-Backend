package com.company.project.service;

import static org.junit.Assert.*;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.project.util.JWTUtil;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmailServiceTest {
	@Autowired
	private EmailService emailService;
	private int hashIterations = 2;

	@Test
	public void testSendActivationEmail() {
		String email = "alicialuo1529@gmail.com";
	   	// check case with correct passoword
		String password = "alicialuo1529@gmail.com_password";
		SimpleHash hashedPassword = new SimpleHash("md5",password, ByteSource.Util.bytes(email),hashIterations);
		String secret = hashedPassword.toHex();
		String token = JWTUtil.sign(email, secret);
		emailService.sendActivationEmail(email, token);
	}
}
