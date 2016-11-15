package com.lo.deviscan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lo.deviscan.beans.Bill;
import com.lo.deviscan.beans.BillDao;
import com.lo.deviscan.beans.Center;
import com.lo.deviscan.beans.CenterDao;
import com.lo.deviscan.beans.Doctor;
import com.lo.deviscan.beans.DoctorDao;
import com.lo.deviscan.beans.Test;
import com.lo.deviscan.beans.TestDao;
import com.lo.deviscan.beans.User;
import com.lo.deviscan.beans.UserDao;
import com.lo.deviscan.util.Factory;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	private CenterDao centerDao = (CenterDao)Factory.getBean("centerDao"); 
	private UserDao userDao = (UserDao)Factory.getBean("userDao"); 
	private DoctorDao doctorDao = (DoctorDao)Factory.getBean("doctorDao"); 
	private TestDao testDao = (TestDao)Factory.getBean("testDao"); 
	private BillDao billDao = (BillDao)Factory.getBean("billDao"); 
	
	@Override
	public List<Center> getAllCenters() {
		return centerDao.getAll();
	}

	@Override
	public void createCenter(Center center) {
		centerDao.create(center);
	}

	@Override
	public void deleteCenter(String id) {
		centerDao.delete(id);
	}

	@Override
	public void updateCenter(Center center) {
		centerDao.update(center);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public void createUser(User user) {
		userDao.create(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(String username) {
		userDao.delete(username);
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorDao.getAll();
	}

	@Override
	public void createDoctor(Doctor doctor) {
		doctorDao.create(doctor);
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		doctorDao.update(doctor);
	}

	@Override
	public void deleteDoctor(String id) {
		doctorDao.delete(id);
	}

	@Override
	public User checkForUser(String username) {
		User user = null;
		user = userDao.get(username);
		return user;
	}

	@Override
	public List<Test> getAllTests() {
		return testDao.getAll();
	}

	@Override
	public void createTest(Test test) {
		testDao.create(test);
	}

	@Override
	public void updateTest(Test test) {
		testDao.update(test);
	}

	@Override
	public void deleteTest(String id) {
		testDao.delete(id);
	}

	@Override
	public List<Bill> getAllBills() {
		return billDao.getAll();
	}

}
