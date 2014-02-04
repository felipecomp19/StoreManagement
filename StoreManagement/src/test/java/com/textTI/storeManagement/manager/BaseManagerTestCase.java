package com.textTI.storeManagement.manager;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/*@ContextConfiguration(
        locations = {"classpath:/applicationContext-resources.xml",
                "classpath:/applicationContext-dao.xml",
                "classpath:/applicationContext-service.xml",
                "classpath*:/applicationContext.xml", // for modular archetypes
                "/WEB-INF/applicationContext*.xml",
                "/WEB-INF/dispatcher-servlet.xml"})*/
@ContextConfiguration(locations = {"classpath:/applicationContext-resources.xml","/WEB-INF/spring/appServlet/servlet-context.xml"})
public abstract class BaseManagerTestCase extends AbstractTransactionalJUnit4SpringContextTests {
    
	protected static final Logger logger = LoggerFactory.getLogger(TestClientManager.class);
	
	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
	/*protected transient final Log log = LogFactory.getLog(getClass());
    private int smtpPort = 25250;

    @Before
    public void onSetUp() {
        smtpPort = smtpPort + (int) (Math.random() * 100);
        // change the port on the mailSender so it doesn't conflict with an
        // existing SMTP server on localhost
        JavaMailSenderImpl mailSender = (JavaMailSenderImpl) applicationContext.getBean("mailSender");
        mailSender.setPort(getSmtpPort());
        mailSender.setHost("localhost");
    }

    protected int getSmtpPort() {
        return smtpPort;
    }*/
}
