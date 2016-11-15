package com.lo.deviscan.service;

import org.springframework.stereotype.Service;

import com.lo.deviscan.beans.User;
import com.lo.deviscan.beans.UserDao;
import com.lo.deviscan.util.Factory;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	private UserDao userDao = (UserDao)Factory.getBean("userDao"); 
	
	@Override
	public boolean validateUser(String username, String password) {
		boolean valid = false;
	 	User user = userDao.get(username);
	    if(user != null && user.getPassword().equalsIgnoreCase(password)){
	    	valid = true;
	    }
		return valid;
	}

	@Override
	public User getUser(String username) {
		return userDao.get(username);
	}

}
