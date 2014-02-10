package com.textTI.storeManagement.manager;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.AuditDAO;
import com.textTI.storeManagement.model.Audit;
import com.textTI.storeManagement.model.User;

@Component
public class AuditManager {
	
	@Autowired
	private MessageSource msgSrc;
	
	@Autowired
	private AuditDAO auditDAO;
	
	/***
	 * @param operation user MessageConstant
	 * @param user that made the operation
	 */
	public void audit(String operation, User user)
	{
		Audit audit = new Audit();
		audit.setOperation(operation);
		audit.setUser(user);
		audit.setDate(new Date());
		
		this.auditDAO.insert(audit);
	}
	
	
	public List<Audit> getTOP50(Locale locale)
	{
		List<Audit> top50 = this.auditDAO.getTOP50();
		
		for (Audit audit : top50) {
			audit.setOperation(msgSrc.getMessage(audit.getOperation(),null, locale));
		}
		
		return top50;
	}
}
