package com.textTI.storeManagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.textTI.ecommerce.models.PlanType;
import com.textTI.storeManagement.manager.UserManager;
import com.textTI.storeManagement.model.User;

@Controller
public class BaseController extends HandlerInterceptorAdapter {

	protected static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserManager userManager;
	
	private User loggedUser;
	
	public User getLoggedUser(HttpServletRequest request) {
		this.loggedUser = (User) request.getSession().getAttribute("loggedUser");
		
		if(this.loggedUser == null){
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();

			this.loggedUser = this.userManager.getByUserName(userName);
			request.getSession().setAttribute("loggedUser", this.loggedUser);
		}
		
		return this.loggedUser;
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		if(modelAndView != null){
			modelAndView.addObject("loggedUser", this.getLoggedUser(request));
			modelAndView.addObject("planTypeBAISIC", PlanType.BASIC);
		}
	}
}
