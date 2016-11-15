package com.lo.deviscan.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.lo.deviscan.beans.Center;
import com.lo.deviscan.beans.CollectionReport;
import com.lo.deviscan.beans.Doctor;
import com.lo.deviscan.beans.Test;
import com.lo.deviscan.beans.User;
import com.lo.deviscan.service.AdminService;


@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/admin/center/", method = RequestMethod.GET)
    public ResponseEntity<List<Center>> listAllCenters() {
        List<Center> centers = adminService.getAllCenters();
        if(centers.isEmpty()){
            return new ResponseEntity<List<Center>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Center>>(centers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/admin/center/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCenter(@RequestBody Center center,    UriComponentsBuilder ucBuilder) {
        if(center.getId() > 0){
        	adminService.updateCenter(center);
        }else{
        	adminService.createCenter(center);
        }
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/admin/center/{name}").buildAndExpand(center.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/admin/center/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Center> deleteCenter(@PathVariable("id") String id) {
    	adminService.deleteCenter(id);
        return new ResponseEntity<Center>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/admin/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = adminService.getAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/admin/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
    	User existingUser = adminService.checkForUser(user.getUsername());
//    	if(existingUser != null && existingUser.getUsername() != null && existingUser.getUsername().equalsIgnoreCase(user.getUsername())){
//    		adminService.updateUser(user);
//    	}else{
    		adminService.createUser(user);
 //   	}
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/admin/user/{name}").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/admin/user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("username") String username) {
    	adminService.deleteUser(username);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
	
	
    @RequestMapping(value = "/admin/doctor/", method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> listAllDoctors() {
        List<Doctor> doctors = adminService.getAllDoctors();
        if(doctors.isEmpty()){
            return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/admin/doctor/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDoctor(@RequestBody Doctor doctor,    UriComponentsBuilder ucBuilder) {
        if(doctor.getId() > 0){
        	adminService.updateDoctor(doctor);
        }else{
        	adminService.createDoctor(doctor);
        }
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/admin/doctor/{name}").buildAndExpand(doctor.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/admin/doctor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") String id) {
    	adminService.deleteDoctor(id);
        return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/admin/test/", method = RequestMethod.GET)
    public ResponseEntity<List<Test>> listAllTests() {
        List<Test> tests = adminService.getAllTests();
        if(tests.isEmpty()){
            return new ResponseEntity<List<Test>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Test>>(tests, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/admin/test/", method = RequestMethod.POST)
    public ResponseEntity<Void> createTest(@RequestBody Test test,    UriComponentsBuilder ucBuilder) {
        if(test.getId() > 0){
        	adminService.updateTest(test);
        }else{
        	adminService.createTest(test);
        }
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/admin/test/{name}").buildAndExpand(test.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
      
    @RequestMapping(value = "/admin/test/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Test> deleteTest(@PathVariable("id") String id) {
    	adminService.deleteTest(id);
        return new ResponseEntity<Test>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/admin/report/collection/", method = RequestMethod.GET)
    public ResponseEntity<List<CollectionReport>> getTodaysCollection(HttpServletRequest request) {
    	User loggedinUser = (com.lo.deviscan.beans.User)request.getSession().getAttribute("LOGGEDIN_USER");
        //Double todaysCollection = userService.getTodaysCollection(loggedinUser);
    	Date todaysDate = Calendar.getInstance().getTime();
    	todaysDate.setHours(0);
    	todaysDate.setMinutes(0);
    	todaysDate.setSeconds(0);
    	List<Bill> bills = adminService.getAllBills();
    	Map<String, CollectionReport> collectionReportMap = new HashMap<String, CollectionReport>();
    	for (Bill bill : bills) {
    		if(bill.getCreatedDate().after(todaysDate)){
				if(collectionReportMap.get(bill.getCreatedBy().getUsername()) == null){
					CollectionReport cr = new CollectionReport();
					cr.setUser(bill.getCreatedBy());
					collectionReportMap.put(bill.getCreatedBy().getUsername(), cr);
				}
				CollectionReport collectionReport = collectionReportMap.get(bill.getCreatedBy().getUsername());
				collectionReport.getBills().add(bill);
				collectionReport.setAmount(collectionReport.getAmount() + bill.getAmount());;
			}
		}
    	return new ResponseEntity<List<CollectionReport>>(new ArrayList(collectionReportMap.values()), HttpStatus.OK);
    }
}
