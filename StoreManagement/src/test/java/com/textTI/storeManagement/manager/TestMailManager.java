package com.textTI.storeManagement.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.Campaign;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.MailingList;

public class TestMailManager extends BaseManagerTestCase{
	
	@Autowired
	private MailManager mailManager;
	
	@Test
	public void testSendMail()
	{
		Campaign campaign = new Campaign();
		MailingList ml = createMailingList();
		
		campaign.setMailingList(ml);
		campaign.setName("Test send mail");
		campaign.setEmailContent("<html><head></head><body><h1>Hello world!!!</h1></body></html>");
		
//		try {
//			this.mailManager.sendHTMLMail(campaign);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
	}
	
	@Test
	public void testSendAsyncMail()
	{
		Calendar aux = Calendar.getInstance();
		System.out.println(aux.get(Calendar.YEAR));
	}
	
	public static void main(String args[])
	{
		Calendar aux = Calendar.getInstance();
		aux.set(aux.get(Calendar.YEAR),aux.get(Calendar.MONTH), aux.get(Calendar.DATE), 0,1);
		aux.add(Calendar.DATE, 1);
		
		System.out.println(aux.get(Calendar.YEAR));
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println(dateFormat.format(aux.getTime()));
	}

	private MailingList createMailingList() {
		MailingList ml = new MailingList();
		ml.setDefaultFromEmail("morana@moranavale.com.br");
		ml.setDefaultFromName("Morana@moranavale.com.br");
		ml.setDefaultSubject("Teste send mail");
		
		List<Client> clients = new ArrayList<Client>();
		createClients(clients);
		
		ml.setClients(clients);
		
		return ml;
	}
	
	private void createClients(List<Client> clients) {
		Client cli = new Client();
		cli.setEmail("felipecomp19@gmail.com");
		clients.add(cli);
//		Client cli2 = new Client();
//		cli2.setEmail("flavia.carnielli@gmail.com");
//		clients.add(cli2);
	}

}
