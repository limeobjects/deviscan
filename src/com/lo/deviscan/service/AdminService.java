package com.lo.deviscan.service;

import java.util.List;

import com.lo.deviscan.beans.Bill;
import com.lo.deviscan.beans.Center;
import com.lo.deviscan.beans.Doctor;
import com.lo.deviscan.beans.Test;
import com.lo.deviscan.beans.User;

public interface AdminService {

	public List<Center> getAllCenters();
	public void createCenter(Center center);
	public void updateCenter(Center center);
	public void deleteCenter(String id);
	
	public List<Doctor> getAllDoctors();
	public void createDoctor(Doctor doctor);
	public void updateDoctor(Doctor doctor);
	public void deleteDoctor(String id);
	
	public List<User> getAllUsers();
	public void createUser(User user);
	public void updateUser(User user);
	public void deleteUser(String username);
	public User checkForUser(String username);
	
	public List<Test> getAllTests();
	public void createTest(Test test);
	public void updateTest(Test test);
	public void deleteTest(String id);
	
	public List<Bill> getAllBills();
	
}
