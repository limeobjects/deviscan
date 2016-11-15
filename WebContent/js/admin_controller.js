'use strict';
 
angular.module('deviscan').controller('AdminController', ['$scope', 'AdminService', function($scope, AdminService) {
	
	var self = this;
	
	self.getTodaysCollection = getTodaysCollection;
	self.collectionReports =[];
	
	self.testType = {name:"Center-Incharge"};
	self.testTypes = [{name:"Lab"}, {name:"Scan"}];
	
	self.userType = {name:"Center-Incharge"};
	self.userTypes = [{name:"Center-Incharge"}, {name:"Center-User"}, {name:"Administrator"}];
	
	self.doctorType = {name:"Refering"};
	self.doctorTypes = [{name:"Refering"}, {name:"Scanning"}];
	
	self.center={id:0,name:"",details:""};
    self.centers=[];
 
    self.submitCenter = submitCenter;
    self.editCenter = editCenter;
    self.removeCenter = removeCenter;
    self.resetCenter = resetCenter;
    
    self.doctor={id:0,name:"",type:"",address:"",mobile:""};
    self.doctors=[];
 
    self.submitDoctor = submitDoctor;
    self.editDoctor = editDoctor;
    self.removeDoctor = removeDoctor;
    self.resetDoctor = resetDoctor;
    
    self.user={fullname:"",username:null,password:"",role:"",center:{id:null,name:""}};
    self.users=[];
 
    self.submitUser = submitUser;
    self.editUser = editUser;
    self.removeUser = removeUser;
    self.resetUser = resetUser;
    
    self.test={id:0,name:"",type:"",rate:0,jssk:0,rsby:0,rbsk:0,tribal:0};
    self.tests=[];
 
    self.submitTest = submitTest;
    self.editTest = editTest;
    self.removeTest = removeTest;
    self.resetTest = resetTest;
    
    fetchAllCenters();
    
    fetchAllUsers();
    
    fetchAllDoctors();
    
    fetchAllTests();
    
    function getTodaysCollection(){
        AdminService.getTodaysCollection()
            .then(
            function(d) {
                self.collectionReports = d;
            },
            function(errResponse){
                console.error('Error while fetching Todays Collection');
            }
        );
    }
    
    function fetchAllCenters(){
        AdminService.fetchAllCenters()
            .then(
            function(d) {
                self.centers = d;
            },
            function(errResponse){
                console.error('Error while fetching Centers');
            }
        );
    }
    
    function submitCenter() {
        saveCenter(self.center);
        resetCenter();
    }
    
    function saveCenter(center){
        AdminService.saveCenter(center)
            .then(
            fetchAllCenters,
            function(errResponse){
                console.error('Error while creating Center');
            }
        );
    }
    
    function resetCenter(){
        self.center={id:0,name:"",description:""};
        $scope.newCenter.$setPristine(); //reset Form
    }
    
    function editCenter(id){
        for(var i = 0; i < self.centers.length; i++){
            if(self.centers[i].id === id) {
                self.center = angular.copy(self.centers[i]);
                break;
            }
        }
    }
 
    function removeCenter(id){
    	if (confirm("Are you absolutely sure you want to delete?")) {
        	AdminService.deleteCenter(id)
           	.then(
         		fetchAllCenters,
            	function(errResponse){
         			console.error('Error while creating Center');
           		}
            );
        	resetCenter();
        }
    }
    
    function fetchAllDoctors(){
        AdminService.fetchAllDoctors()
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
        AdminService.saveDoctor(doctor)
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
        	AdminService.deleteDoctor(id)
           	.then(
         		fetchAllDoctors,
            	function(errResponse){
         			console.error('Error while creating Doctor');
           		}
            );
        	resetDoctor();
        }
    }
    
    function fetchAllUsers(){
        AdminService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    
    function submitUser() {
        saveUser(self.user);
        resetUser();
    }
    
    function saveUser(user){
        AdminService.saveUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }
    
    function resetUser(){
    	self.user={fullname:"",username:null,password:"",role:"",center:{id:null,name:""}};
        $scope.newUser.$setPristine(); //reset Form
    }
    
    function editUser(username){
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].username === username) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
 
    function removeUser(id){
    	if (confirm("Are you absolutely sure you want to delete?")) {
        	AdminService.deleteUser(id)
           	.then(
         		fetchAllUsers,
            	function(errResponse){
         			console.error('Error while creating User');
           		}
            );
        	resetUser();
        }
    }
    
    function fetchAllTests(){
        AdminService.fetchAllTests()
            .then(
            function(d) {
                self.tests = d;
            },
            function(errResponse){
                console.error('Error while fetching Doctors');
            }
        );
    }
    
    function submitTest() {
        saveTest(self.test);
        resetTest();
    }
    
    function saveTest(test){
        AdminService.saveTest(test)
            .then(
            fetchAllTests,
            function(errResponse){
                console.error('Error while creating Test');
            }
        );
    }
    
    function resetTest(){
        self.test={id:0,name:"",type:"",rate:0,jssk:0,rsby:0,rbsk:0,tribal:0};
        $scope.newTest.$setPristine(); //reset Form
    }
    
    function editTest(id){
        for(var i = 0; i < self.tests.length; i++){
            if(self.tests[i].id === id) {
                self.test = angular.copy(self.tests[i]);
                break;
            }
        }
    }
 
    function removeTest(id){
    	if (confirm("Are you absolutely sure you want to delete?")) {
        	AdminService.deleteTest(id)
           	.then(
         		fetchAllTests,
            	function(errResponse){
         			console.error('Error while creating Test');
           		}
            );
        	resetTest();
        }
    }
 
}]);