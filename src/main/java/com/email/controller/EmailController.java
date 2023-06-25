package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.entity.EmailRequest;
import com.email.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/admin")
public class EmailController {
	@Autowired
	private EmailService mailSenderService;

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {
		return ResponseEntity.ok(mailSenderService.sendEmailWithAttachment(emailRequest));
	}
}
