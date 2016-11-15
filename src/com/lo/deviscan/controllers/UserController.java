package com.lo.deviscan.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lo.deviscan.beans.Bill;
import com.lo.deviscan.beans.BillTest;
import com.lo.deviscan.beans.Center;
import com.lo.deviscan.beans.Doctor;
import com.lo.deviscan.beans.Patient;
import com.lo.deviscan.beans.Test;
import com.lo.deviscan.beans.User;
import com.lo.deviscan.service.AdminService;
import com.lo.deviscan.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/lab/", method = RequestMethod.GET)
    public ResponseEntity<List<Test>> listAllLabTests() {
        List<Test> tests = userService.getAllTests();
        if(tests.isEmpty()){
            return new ResponseEntity<List<Test>>(HttpStatus.NO_CONTENT);
        }
        List<Test> labTests = new ArrayList<Test>();
        for (Test test : tests) {
			if(test.getType().equalsIgnoreCase("Lab")){
				labTests.add(test);
			}
		}
        return new ResponseEntity<List<Test>>(labTests, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/user/scan/", method = RequestMethod.GET)
    public ResponseEntity<List<Test>> listAllScanTests() {
        List<Test> tests = userService.getAllTests();
        if(tests.isEmpty()){
            return new ResponseEntity<List<Test>>(HttpStatus.NO_CONTENT);
        }
        List<Test> scanTests = new ArrayList<Test>();
        for (Test test : tests) {
			if(test.getType().equalsIgnoreCase("Scan")){
				scanTests.add(test);
			}
		}
        return new ResponseEntity<List<Test>>(scanTests, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/user/doctor/staff/", method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> listAllStaffDoctors() {
        List<Doctor> doctors = userService.getAllDoctors();
        if(doctors.isEmpty()){
            return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
        }
        List<Doctor> staffDoctors = new ArrayList<Doctor>();
        for (Doctor doctor : doctors) {
			if(doctor.getType().equalsIgnoreCase("Scanning")){
				staffDoctors.add(doctor);
			}
		}
        return new ResponseEntity<List<Doctor>>(staffDoctors, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/user/doctor/", method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> listAllDoctors() {
        List<Doctor> doctors = userService.getAllDoctors();
        if(doctors.isEmpty()){
            return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
        }
        List<Doctor> refDoctors = new ArrayList<Doctor>();
        for (Doctor doctor : doctors) {
			if(doctor.getType().equalsIgnoreCase("Refering")){
				refDoctors.add(doctor);
			}
		}
        return new ResponseEntity<List<Doctor>>(refDoctors, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/doctor/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDoctor(@RequestBody Doctor doctor,    UriComponentsBuilder ucBuilder) {
    	doctor.setType("Refering");
        if(doctor.getId() > 0){
        	userService.updateDoctor(doctor);
        }else{
        	userService.createDoctor(doctor);
        }
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/doctor/{name}").buildAndExpand(doctor.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/doctor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") String id) {
    	userService.deleteDoctor(id);
        return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/user/patient/", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> listAllPatients() {
        List<Patient> patients = userService.getAllPatients();
        if(patients.isEmpty()){
            return new ResponseEntity<List<Patient>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/patient/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPatient(@RequestBody Patient patient,    UriComponentsBuilder ucBuilder) {
    	if(patient.getId() > 0){
        	userService.updatePatient(patient);
        }else{
        	userService.createPatient(patient);
        }
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/patient/{name}").buildAndExpand(patient.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/patient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") String id) {
    	userService.deletePatient(id);
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/user/bill/", method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> listAllBills() {
        List<Bill> bills = userService.getAllBills();
        if(bills.isEmpty()){
            return new ResponseEntity<List<Bill>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/bill/", method = RequestMethod.POST)
    public ResponseEntity<Void> createBill(@RequestBody Bill bill,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
    	User loggedinUser = (com.lo.deviscan.beans.User)request.getSession().getAttribute("LOGGEDIN_USER");
    	bill.setCreatedBy(loggedinUser);
    	if(bill.getId() > 0){
        	userService.updateBill(bill);
        }else{
        	userService.createBill(bill);
        }
    	for (BillTest billTest : bill.getBillTests()) {
			billTest.setBill(bill);
			userService.createBillTest(billTest);
		}
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/bill/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Bill> deleteBill(@PathVariable("id") String id) {
    	userService.deleteBill(id);
        return new ResponseEntity<Bill>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/user/billTest/", method = RequestMethod.GET)
    public ResponseEntity<List<BillTest>> listAllBillTests() {
        List<BillTest> billTests = userService.getAllBillTests();
        if(billTests.isEmpty()){
            return new ResponseEntity<List<BillTest>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<BillTest>>(billTests, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/billTest/", method = RequestMethod.POST)
    public ResponseEntity<Void> createBillTest(@RequestBody BillTest billTest, UriComponentsBuilder ucBuilder) {
    	if(billTest.getId() > 0){
        	userService.updateBillTest(billTest);
        }else{
        	userService.createBillTest(billTest);
        }
  
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/billTest/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BillTest> deleteBillTest(@PathVariable("id") String id) {
    	userService.deleteBillTest(id);
        return new ResponseEntity<BillTest>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/user/report/collection/", method = RequestMethod.GET)
    public ResponseEntity<Double> getTodaysCollection(HttpServletRequest request) {
    	User loggedinUser = (com.lo.deviscan.beans.User)request.getSession().getAttribute("LOGGEDIN_USER");
        //Double todaysCollection = userService.getTodaysCollection(loggedinUser);
    	Double todaysCollection = new Double(0);
    	Date todaysDate = Calendar.getInstance().getTime();
    	todaysDate.setHours(0);
    	todaysDate.setMinutes(0);
    	todaysDate.setSeconds(0);
    	List<Bill> bills = userService.getAllBills();
    	List<Bill> selectedBills = new ArrayList<Bill>();
    	if(loggedinUser.getRole().equalsIgnoreCase("Center-Incharge")){
    		for (Bill bill : bills) {
				if(bill.getCreatedBy().getCenter().getName().equalsIgnoreCase(loggedinUser.getCenter().getName()) && bill.getCreatedDate().after(todaysDate)){
					selectedBills.add(bill);
					todaysCollection += bill.getAmount();
				}
			}
    	}else if(loggedinUser.getRole().equalsIgnoreCase("Center-User")){
	    	for (Bill bill : bills) {
				if(bill.getCreatedBy().getUsername().equalsIgnoreCase(loggedinUser.getUsername()) && bill.getCreatedDate().after(todaysDate)){
					selectedBills.add(bill);
					todaysCollection += bill.getAmount();
				}
			}
    	}
    	return new ResponseEntity<Double>(todaysCollection, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/bill/pending", method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> listPendingBills() {
        List<Bill> bills = userService.getAllBills();
        List<Bill> pendingBills = new ArrayList<Bill>();
        Date todaysDate = Calendar.getInstance().getTime();
    	todaysDate.setHours(0);
    	todaysDate.setMinutes(0);
    	todaysDate.setSeconds(0);
        if(bills.isEmpty()){
            return new ResponseEntity<List<Bill>>(HttpStatus.NO_CONTENT);
        }
        for (Bill bill : bills) {
			if(bill.getCreatedDate().after(todaysDate) && (bill.getStatus() == null || bill.getStatus().isEmpty())){
				pendingBills.add(bill);
			}
		}
        return new ResponseEntity<List<Bill>>(pendingBills, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/bill/updateStatus/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Bill> updateBillStatus(@PathVariable("id") String id) {
    	userService.updateBillStatus(id);
        return new ResponseEntity<Bill>(HttpStatus.NO_CONTENT);
    }
}
