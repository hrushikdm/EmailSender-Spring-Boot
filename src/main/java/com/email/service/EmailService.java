package com.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.email.entity.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private final JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	
	public String sendEmailWithAttachment(EmailRequest emailRequest) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(emailRequest.getTo());
		helper.setSubject(emailRequest.getSubject());
		helper.setText(emailRequest.getBody());
	//	helper.addAttachment("Image","/Test/pom.xml" );
		if (emailRequest.getAttachmentData() != null && emailRequest.getAttachmentName() != null) {
			InputStreamSource attachmentSource = new ByteArrayResource(emailRequest.getAttachmentData());
			helper.addAttachment(emailRequest.getAttachmentName(), attachmentSource);
		}
		javaMailSender.send(message);
		return "Email Send Sucessfully";
	}
}
