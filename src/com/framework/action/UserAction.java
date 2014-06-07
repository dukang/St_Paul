package com.framework.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.pojo.User;
import com.framework.service.LoginService;

@Controller
@RequestMapping("/jsp/user") 
public class UserAction 
{
	
	@Autowired  
	@Qualifier("loginserviceimpl") 
	LoginService loginserviceimpl;

	
	@RequestMapping("/register")
	public ModelAndView RegisterUser(@ModelAttribute("user") User user)
	{
		ModelAndView mav = new ModelAndView("index");
		
		if(!loginserviceimpl.register(user))
		{
			
			return null;
		}
		
		
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView UserLogin(@ModelAttribute("user") User user)
	{
		ModelAndView mav; 
		
		//username is not exist
		if(!loginserviceimpl.login(user))
		{
			return null;
		}
		
		
		mav = new ModelAndView("index");
		
		return mav;
	}
	
	
}
