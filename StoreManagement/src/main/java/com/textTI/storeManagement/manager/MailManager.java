package com.textTI.storeManagement.manager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.model.Campaign;
import com.textTI.storeManagement.model.Client;

@Component
public class MailManager {
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	public void sendTextMail(Campaign campaign)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(campaign.getMailingList().getDefaultFromName());
		message.setSubject(campaign.getName());
		message.setText(campaign.getEmailContent());
		
		for (Client cli : campaign.getMailingList().getClients()) {
			message.setTo(cli.getEmail());
			mailSender.send(message);
		}
	}
	
	public void sendHTMLMail(Campaign campaign) throws MessagingException
	{
		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,false, "utf-8");
		
		helper.setFrom(campaign.getMailingList().getDefaultFromName());
		//helper.setFrom("Morana@moranavale.com.br");
		helper.setSubject(campaign.getName());
		helper.setText(campaign.getEmailContent(), true);
		
		for (Client cli : campaign.getMailingList().getClients()) {
			helper.setTo(cli.getEmail());
			mailSender.send(mimeMessage);
		}
	}

}
