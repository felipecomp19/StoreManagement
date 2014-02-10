package com.textTI.storeManagement.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.message.MessageConstant;
import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.model.UserRole;

public class TestAuditManager extends BaseManagerTestCase {

	@Autowired
	private AuditManager auditManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UserRoleManager roleManager;
	
	@Test
	public void testInsertAudit()
	{
//		UserRole role = this.roleManager.getAll().get(0);
//		User user = new User();
//		user.setUserName("felipecomp19");
//		user.setUserRole(role);
//		user.setPassword("123456");
		
		//this.userManager.insert(user);
		
		//this.auditManager.audit(MessageConstant.CREATED_A_CLIENT, user);
		
	}
}
