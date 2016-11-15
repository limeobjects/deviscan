package com.lo.deviscan.service;

import java.util.List;

import com.lo.deviscan.beans.Bill;
import com.lo.deviscan.beans.BillTest;
import com.lo.deviscan.beans.Center;
import com.lo.deviscan.beans.Doctor;
import com.lo.deviscan.beans.Patient;
import com.lo.deviscan.beans.Test;
import com.lo.deviscan.beans.User;

public interface UserService {

	public List<Doctor> getAllDoctors();
	public void createDoctor(Doctor doctor);
	public void updateDoctor(Doctor doctor);
	public void deleteDoctor(String id);
	
	
	public List<Patient> getAllPatients();
	public void createPatient(Patient patient);
	public void updatePatient(Patient patient);
	public void deletePatient(String id);
	
	public List<Test> getAllTests();
	
	public List<Bill> getAllBills();
	public void createBill(Bill bill);
	public void updateBill(Bill bill);
	public void deleteBill(String id);
	public Bill getBill(String id);
	public void updateBillStatus(String id);
	
	public List<BillTest> getAllBillTests();
	public void createBillTest(BillTest billTest);
	public void updateBillTest(BillTest billTest);
	public void deleteBillTest(String id);
	
	public Double getTodaysCollection(User loggedinUser);
}
