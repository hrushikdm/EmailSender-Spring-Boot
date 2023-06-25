package com.email.entity;

import lombok.Data;

@Data
public class EmailRequest {

	private String to;
	private String subject;
	private String body;
	private byte[] attachmentData;
	private String attachmentName;
}
