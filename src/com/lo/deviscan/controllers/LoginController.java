package com.lo.deviscan.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lo.deviscan.beans.Authentication;
import com.lo.deviscan.beans.User;
import com.lo.deviscan.beans.UserDao;
import com.lo.deviscan.service.LoginService;
import com.lo.deviscan.util.Factory;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	   public String loadPage(@ModelAttribute("authentication")Authentication authentication, ModelMap model, HttpServletRequest request) {
		request.getSession().removeAttribute("LOGGEDIN_USER");
	    model.addAttribute("authentication", authentication); 
		return "login";
	  }
	
	 @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	   public String authenticate(@ModelAttribute("authentication")Authentication authentication, ModelMap model, HttpServletRequest request) {
		    boolean valid = loginService.validateUser(authentication.getUsername(), authentication.getPassword());
		    String redirectPath = "redirect:/userHome";
		    if(valid){
		    	User user = loginService.getUser(authentication.getUsername());
		    	if(user.getRole().equalsIgnoreCase("Administrator")){
			    	redirectPath = "redirect:/admin";
			    }
		    	if(user.getRole().equalsIgnoreCase("Center-User")){
			    	redirectPath = "redirect:/user";
			    }
		    	if(user.getRole().equalsIgnoreCase("Center-Incharge")){
			    	redirectPath = "redirect:/incharge";
			    }
		    	request.getSession().setAttribute("LOGGEDIN_USER", user);
		    }else{
		    	model.addAttribute("LOGIN_STATUS", "Invalid Credentials");
		    	redirectPath = "login";
		    }
		 return redirectPath;
	  }

	 @RequestMapping(value = "/admin", method = RequestMethod.GET)
	   public String loadAdmin(@ModelAttribute("user")User user, ModelMap model, HttpServletRequest request) {
		User loggedinUser = (com.lo.deviscan.beans.User)request.getSession().getAttribute("LOGGEDIN_USER");
		model.addAttribute("loggedinUser", loggedinUser);
	    model.addAttribute("user", user);
	    return "admin";
	  }
	 
	 @RequestMapping(value = "/user", method = RequestMethod.GET)
	   public String loadUser(@ModelAttribute("user")User user, ModelMap model, HttpServletRequest request) {
		User loggedinUser = (com.lo.deviscan.beans.User)request.getSession().getAttribute("LOGGEDIN_USER");
		model.addAttribute("loggedinUser", loggedinUser);
	    model.addAttribute("user", user);
	    return "user";
	  }
	 
	 @RequestMapping(value = "/incharge", method = RequestMethod.GET)
	   public String loadPage(@ModelAttribute("user")User user, ModelMap model, HttpServletRequest request) {
		User loggedinUser = (com.lo.deviscan.beans.User)request.getSession().getAttribute("LOGGEDIN_USER");
		model.addAttribute("loggedinUser", loggedinUser);
	    model.addAttribute("user", user);
	    return "incharge";
	  }
}
