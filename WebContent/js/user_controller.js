'use strict';
 
angular.module('deviscan').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
	
	var self = this;
	
	self.todaysCollection = 0;
	
	self.getTodaysCollection = getTodaysCollection;
	
	self.bill = {type:"NORMAL", amount:0};
	
	self.submitBill = submitBill;
    self.editBill = editBill;
    self.removeBill = removeBill;
    self.resetBill = resetBill;
	
	self.genderType = {name:"Male"};
	self.genderTypes = [{name:"Male"}, {name:"Female"}];
	
	self.billingType = {name:"NORMAL"};
	self.billingTypes = [{name:"NORMAL"}, {name:"JSSK"}, {name:"RSBY"}, {name:"RBSK"}, {name:"TRIBAL"}];
	   
    self.doctor={id:0,name:"",type:"",address:"",mobile:""};
    self.doctors=[];
    self.staffDoctors=[];
 
    self.submitDoctor = submitDoctor;
    self.editDoctor = editDoctor;
    self.removeDoctor = removeDoctor;
    self.resetDoctor = resetDoctor;
    
    self.patient={id:0,name:"",age:"",address:"",mobile:"",gender:""};
    self.patients=[];
 
    self.submitPatient = submitPatient;
    self.editPatient = editPatient;
    self.removePatient = removePatient;
    self.resetPatient = resetPatient;
    
    self.labTests=[];
    self.scanTests=[];
    self.selectedLabTests=[];
    self.selectedScanTests=[];
    self.selectedTests=[];
    self.removeLabTest = removeLabTest;
    self.removeScanTest = removeScanTest;
    
    self.pendingBills = [];
    self.fetchPendingBills = fetchPendingBills;
    self.updateBillStatus = updateBillStatus;
    
    fetchPendingBills();
    
    fetchAllDoctors();
    
    fetchAllPatients();
    
    fetchAllStaffDoctors();
    
    fetchAllLabTests();
    
    fetchAllScanTests();
    
    fetchAllBills();
    
    $scope.afterBillingTypeSelect = function(item){
    	self.bill.type = item.name;
    }
    
    $scope.afterPatientSelect = function(item){
    	self.bill.patient = item;
    }
    
    $scope.afterRefDoctorSelect = function(item){
    	self.bill.refDoctor = item;
    }
    
    $scope.afterStaffDoctorSelect = function(item){
    	self.bill.staffDoctor = item;
    }
    
    $scope.afterLabSelectItem = function(item){
    	self.selectedLabTests.push(item);
    	self.bill.amount = parseFloat(self.bill.amount) + parseFloat(item.rate);
    }
    
    $scope.afterScanSelectItem = function(item){
    	self.selectedScanTests.push(item);
    	self.bill.amount = parseFloat(self.bill.amount) + parseFloat(item.rate);
    }
    
    function removeLabTest(id){
    	for(var i = 0; i < self.selectedLabTests.length; i++){
            if(self.selectedLabTests[i].id === id) {
            	self.bill.amount = parseFloat(self.bill.amount) - parseFloat(self.selectedLabTests[i].rate);
            	self.selectedLabTests.splice(i, 1);
                break;
            }
        }
    }
    
    function removeScanTest(id){
    	for(var i = 0; i < self.selectedScanTests.length; i++){
            if(self.selectedScanTests[i].id === id) {
            	self.bill.amount = parseFloat(self.bill.amount) - parseFloat(self.selectedScanTests[i].rate);
            	self.selectedScanTests.splice(i, 1);
                break;
            }
        }
    }
    
    function getTodaysCollection(){
        UserService.getTodaysCollection()
            .then(
            function(d) {
                self.todaysCollection = d;
            },
            function(errResponse){
                console.error('Error while fetching Todays Collection');
            }
        );
    }
    
    function fetchAllLabTests(){
        UserService.fetchAllLabTests()
            .then(
            function(d) {
                self.labTests = d;
            },
            function(errResponse){
                console.error('Error while fetching LabTests');
            }
        );
    }
    
    function fetchAllScanTests(){
        UserService.fetchAllScanTests()
            .then(
            function(d) {
                self.scanTests = d;
            },
            function(errResponse){
                console.error('Error while fetching ScanTests');
            }
        );
    }
    
    function fetchAllStaffDoctors(){
        UserService.fetchAllStaffDoctors()
            .then(
            function(d) {
                self.staffDoctors = d;
            },
            function(errResponse){
                console.error('Error while fetching Doctors');
            }
        );
    }
    
    function fetchAllDoctors(){
        UserService.fetchAllDoctors()
            .then(
            function(d) {
                self.doctors = d;
            },
            function(errResponse){
                console.error('Error while fetching Doctors');
            }
        );
    }
    
    function submitDoctor() {
        saveDoctor(self.doctor);
        resetDoctor();
    }
    
    function saveDoctor(doctor){
        UserService.saveDoctor(doctor)
            .then(
            fetchAllDoctors,
            function(errResponse){
                console.error('Error while creating Doctor');
            }
        );
    }
    
    function resetDoctor(){
        self.doctor={id:0,name:"",type:"",address:"",mobile:""};
        $scope.newDoctor.$setPristine(); //reset Form
    }
    
    function editDoctor(id){
        for(var i = 0; i < self.doctors.length; i++){
            if(self.doctors[i].id === id) {
                self.doctor = angular.copy(self.doctors[i]);
                break;
            }
        }
    }
 
    function removeDoctor(id){
    	if (confirm("Are you absolutely sure you want to delete?")) {
        	UserService.deleteDoctor(id)
           	.then(
         		fetchAllDoctors,
            	function(errResponse){
         			console.error('Error while creating Doctor');
           		}
            );
        	resetDoctor();
        }
    }
    
    function fetchAllPatients(){
        UserService.fetchAllPatients()
            .then(
            function(d) {
                self.patients = d;
            },
            function(errResponse){
                console.error('Error while fetching Patients');
            }
        );
    }
    
    function submitPatient() {
        savePatient(self.patient);
        resetPatient();
    }
    
    function savePatient(patient){
        UserService.savePatient(patient)
            .then(
            fetchAllPatients,
            function(errResponse){
                console.error('Error while creating Patient');
            }
        );
    }
    
    function resetPatient(){
        self.patient={id:0,name:"",age:"",address:"",mobile:"",gender:""};
        $scope.newPatient.$setPristine(); //reset Form
    }
    
    function editPatient(id){
        for(var i = 0; i < self.patients.length; i++){
            if(self.patients[i].id === id) {
                self.patient = angular.copy(self.patients[i]);
                break;
            }
        }
    }
 
    function removePatient(id){
    	if (confirm("Are you absolutely sure you want to delete?")) {
        	UserService.deletePatient(id)
           	.then(
         		fetchAllPatients,
            	function(errResponse){
         			console.error('Error while creating Patient');
           		}
            );
        	resetPatient();
        }
    }
    
    function updateBillStatus(id){
    	if (confirm("Are you absolutely sure you want to mark as done?")) {
        	UserService.updateBillStatus(id)
           	.then(
           		fetchPendingBills,
            	function(errResponse){
         			console.error('Error while updateBillStatus');
           		}
            );
        	resetPatient();
        }
    }
    
    function fetchPendingBills(){
        UserService.fetchPendingBills()
            .then(
            function(d) {
                self.pendingBills = d;
            },
            function(errResponse){
                console.error('Error while fetching PendingBills');
            }
        );
    }
    
    function fetchAllBills(){
        UserService.fetchAllBills()
            .then(
            function(d) {
                self.bills = d;
            },
            function(errResponse){
                console.error('Error while fetching Bills');
            }
        );
    }
    
    function submitBill() {
        saveBill(self.bill);
        fetchPendingBills();
        resetBill();
    }
    
    function saveBill(bill){
    	for(var i = 0; i < self.selectedLabTests.length; i++){
            self.selectedTests.push(self.selectedLabTests[i]);
        }
    	for(var i = 0; i < self.selectedScanTests.length; i++){
            self.selectedTests.push(self.selectedScanTests[i]);
        }
        UserService.saveBill(bill, self.selectedTests)
            .then(
            fetchPendingBills,
            function(errResponse){
                console.error('Error while creating Bill');
            }
        );
    }
    
    function resetBill(){
        self.bill={type:"NORMAL", amount:0};
        self.selectedLabTests=[];
        self.selectedScanTests=[];
        $scope.newBill.$setPristine(); //reset Form
    }
    
    function editBill(id){
        for(var i = 0; i < self.bills.length; i++){
            if(self.bills[i].id === id) {
                self.bill = angular.copy(self.bills[i]);
                break;
            }
        }
    }
 
    function removeBill(id){
    	if (confirm("Are you absolutely sure you want to delete?")) {
        	UserService.deleteBill(id)
           	.then(
         		fetchAllBills,
            	function(errResponse){
         			console.error('Error while creating Bill');
           		}
            );
        	resetPatient();
        }
    }
     
}]);