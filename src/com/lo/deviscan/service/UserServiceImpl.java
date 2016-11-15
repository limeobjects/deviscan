package com.lo.deviscan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lo.deviscan.beans.Bill;
import com.lo.deviscan.beans.BillDao;
import com.lo.deviscan.beans.BillTest;
import com.lo.deviscan.beans.BillTestDao;
import com.lo.deviscan.beans.Center;
import com.lo.deviscan.beans.CenterDao;
import com.lo.deviscan.beans.Doctor;
import com.lo.deviscan.beans.DoctorDao;
import com.lo.deviscan.beans.Patient;
import com.lo.deviscan.beans.PatientDao;
import com.lo.deviscan.beans.Test;
import com.lo.deviscan.beans.TestDao;
import com.lo.deviscan.beans.User;
import com.lo.deviscan.beans.UserDao;
import com.lo.deviscan.util.Factory;

@Service("userService")
public class UserServiceImpl implements UserService{

	private DoctorDao doctorDao = (DoctorDao)Factory.getBean("doctorDao"); 
	private PatientDao patientDao = (PatientDao)Factory.getBean("patientDao"); 
	private TestDao testDao = (TestDao)Factory.getBean("testDao"); 
	private BillDao billDao = (BillDao)Factory.getBean("billDao");
	private BillTestDao billTestDao = (BillTestDao)Factory.getBean("billTestDao");
	
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
	public List<Patient> getAllPatients() {
		return patientDao.getAll();
	}

	@Override
	public void createPatient(Patient patient) {
		patientDao.create(patient);
	}

	@Override
	public void updatePatient(Patient patient) {
		patientDao.update(patient);
	}

	@Override
	public void deletePatient(String id) {
		patientDao.delete(id);
	}

	@Override
	public List<Test> getAllTests() {
		return testDao.getAll();
	}

	@Override
	public List<Bill> getAllBills() {
		return billDao.getAll();
	}

	@Override
	public void createBill(Bill bill) {
		billDao.create(bill);
	}

	@Override
	public void updateBill(Bill bill) {
		billDao.update(bill);
	}

	@Override
	public void deleteBill(String id) {
		billDao.delete(id);
	}

	@Override
	public List<BillTest> getAllBillTests() {
		return billTestDao.getAll();
	}

	@Override
	public void createBillTest(BillTest billTest) {
		billTestDao.create(billTest);
	}

	@Override
	public void updateBillTest(BillTest billTest) {
		billTestDao.update(billTest);
	}

	@Override
	public void deleteBillTest(String id) {
		billTestDao.delete(id);
	}

	@Override
	public Double getTodaysCollection(User loggedinUser) {
		return billDao.getTodaysCollection(loggedinUser);
	}

	@Override
	public Bill getBill(String id) {
		return billDao.get(id);
	}

	@Override
	public void updateBillStatus(String id) {
		Bill bill = getBill(id);
		bill.setStatus("done");
		updateBill(bill);
	}

}
