package com.lo.deviscan.service;

import com.lo.deviscan.beans.User;

public interface LoginService {

	public boolean validateUser(String username, String password);
	
	public User getUser(String username);
}
