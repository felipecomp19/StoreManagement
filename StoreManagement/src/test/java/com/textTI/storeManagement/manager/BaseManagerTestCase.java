package com.textTI.storeManagement.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.model.Address;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.MailingList;
import com.textTI.storeManagement.model.Store;

@ContextConfiguration(locations = {
		"classpath:/applicationContext-resources.xml",
		"/WEB-INF/spring/appServlet/servlet-context.xml",
		"/WEB-INF/spring/root-context.xml"})
public abstract class BaseManagerTestCase extends
		AbstractTransactionalJUnit4SpringContextTests {

	protected static final Logger logger = LoggerFactory
			.getLogger(TestClientManager.class);

	@Autowired
	private StoreManager storeManager;

	@Autowired
	private ClientTypeManager clientTypeManager;
	
	@Autowired
	private ClientManager clientManager;
	
	@Autowired
	private MailingListManager mailingListManager;
	
	@Autowired
	private EmployeeManager employeeManager;

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	public Set<Store> createAndInsertStores() {
		Store st = new Store();
		st.setName("Morana");
		Set<Store> stores = new HashSet<Store>();
		stores.add(st);
		this.storeManager.insert(st);
		return stores;
	}
	
	public Store createAndInsertStore(String name)
	{
		Store st = new Store();
		st.setName(name);
		st.setEnabled(true);
		this.storeManager.insert(st);
		return st;
	}

	public void deleteStores(Set<Store> stores) {
		for (Store store : stores)
			this.storeManager.delete(store);
	}

	public ClientType createAndInsertClientType() {
		ClientType client = new ClientType();
		client.setName("PF");
		client.setDescription("Pessoa Física");
		this.clientTypeManager.insert(client);
		return client;
	}
	
	public Employee createAndInsertEmployee(Store st) {
		Employee emp = new Employee();
		emp.setName("emp 1");
		emp.setEnabled(true);
		emp.setStore(st);
		this.employeeManager.insert(emp);
		return emp;
	}

	public void deleteClientType(ClientType clientType) {
		this.clientTypeManager.delete(clientType);
	}

	public Client createAndInsertOneClient(String cpf, Set<Store> stores,
			ClientType clientType) {

		Address address = new Address();
		address.setStreet("Rua 1");
		address.setNumber(1);
		address.setCep("12421410");
		address.setCity("São Paulo");
		address.setComplement("nenhum");
		address.setNeighbourhood("Vila");
		address.setState("SP");

		Client c1 = new Client();
		c1.setName("Felipe Teixeira");
		c1.setCpf(cpf);
		c1.setEmail("felipecomp19@gmail.com");
		c1.setAddress(address);
		c1.setStores(stores);
		c1.setClientType(clientType);

		try {
			this.clientManager.insert(c1);
		} catch (ClientException e) {
			e.printStackTrace();
		}

		return c1;
	}
	
	public void deleteClient(Client cli) {
		this.clientManager.delete(cli);
	}
	
	public MailingList createAndInsertMailingList(String listName, List<Client> clients) {
		MailingList ml = new MailingList();
		ml.setName(listName);
		ml.setClients(clients);
		ml.setDefaultFromEmail("felipecomp19@gmail.com");
		ml.setDefaultFromName("Felipe Teixeira");
		ml.setDefaultSubject("Promoção Morana");
		
		logger.info("inserting mailing list");
		this.mailingListManager.insert(ml);
		logger.info("done");

		return ml;
	}
	
	public void deleteMailingList(MailingList mailingList) {
		this.mailingListManager.delete(mailingList);
	}
	/*
	 * protected transient final Log log = LogFactory.getLog(getClass());
	 * private int smtpPort = 25250;
	 * 
	 * @Before public void onSetUp() { smtpPort = smtpPort + (int)
	 * (Math.random() * 100); // change the port on the mailSender so it doesn't
	 * conflict with an // existing SMTP server on localhost JavaMailSenderImpl
	 * mailSender = (JavaMailSenderImpl)
	 * applicationContext.getBean("mailSender");
	 * mailSender.setPort(getSmtpPort()); mailSender.setHost("localhost"); }
	 * 
	 * protected int getSmtpPort() { return smtpPort; }
	 */
}
