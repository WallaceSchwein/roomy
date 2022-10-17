package dev.willi.roomy.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import dev.willi.roomy.email.EmailSender;

@Service
public class EmailService implements EmailSender{
	
	//attribute

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	private final static String MAIL_FAILED_MSG = "The email could not have been sent!";
	
	private final JavaMailSender mailSender;
	
	//constructors

	public EmailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	//methods

	@Override
	@Async //TODO: queue this with automated retrying
	public void send(String to, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email to fulfill your registration at Roomy!");
			helper.setFrom("welcome@roomy.com");

			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			LOGGER.error(MAIL_FAILED_MSG, e);

			throw new IllegalStateException(MAIL_FAILED_MSG);
		}
	}
}