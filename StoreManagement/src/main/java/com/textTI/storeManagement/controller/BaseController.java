package com.textTI.storeManagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.textTI.storeManagement.manager.UserManager;
import com.textTI.storeManagement.model.User;

@Controller
@Scope("session")
public class BaseController extends HandlerInterceptorAdapter {

	protected static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserManager userManager;
	
	private User loggedUser;
	

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		//user from session
		this.loggedUser = (User) request.getSession().getAttribute("loggedUser");
		
		if(this.loggedUser == null){
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.loggedUser = this.userManager.getById(user.getId());
			request.getSession().setAttribute("loggedUser", this.loggedUser);
		}
		
		//modified the exisitng modelAndView
		modelAndView.addObject("loggedUser",this.loggedUser);
	}

}
