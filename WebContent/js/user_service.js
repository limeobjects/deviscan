'use strict';
 
angular.module('deviscan').factory('UserService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI_USER_PATIENT = '/DEVISCAN/user/patient/';
    
    var REST_SERVICE_URI_USER_DOCTOR = '/DEVISCAN/user/doctor/';
    
    var REST_SERVICE_URI_USER_DOCTOR_STAFF = '/DEVISCAN/user/doctor/staff/';
    
    var REST_SERVICE_URI_USER_LAB = '/DEVISCAN/user/lab/';
    
    var REST_SERVICE_URI_USER_SCAN = '/DEVISCAN/user/scan/';
    
    var REST_SERVICE_URI_USER_BILL = '/DEVISCAN/user/bill/';
    
    var REST_SERVICE_URI_USER_BILL_TEST = '/DEVISCAN/user/billTest/';
    
    var REST_SERVICE_URI_USER_REPORT = '/DEVISCAN/user/report/';
    
    var REST_SERVICE_URI_USER_BILL_PENDING = '/DEVISCAN/user/bill/pending/';
    
    var REST_SERVICE_URI_USER_BILL_UPDATESTATUS = '/DEVISCAN/user/bill/updateStatus/';
    
 
    var factory = {
        fetchAllDoctors: fetchAllDoctors,
        saveDoctor: saveDoctor,
        deleteDoctor: deleteDoctor,
        fetchAllPatients: fetchAllPatients,
        savePatient: savePatient,
        deletePatient: deletePatient,
        fetchAllStaffDoctors: fetchAllStaffDoctors,
        fetchAllLabTests: fetchAllLabTests,
        fetchAllScanTests: fetchAllScanTests,
        fetchAllBills: fetchAllBills,
        saveBill: saveBill,
        deleteBill: deleteBill,
        fetchAllBillTests: fetchAllBillTests,
        saveBillTest: saveBillTest,
        deleteBillTest: deleteBillTest,
        getTodaysCollection: getTodaysCollection,
        fetchPendingBills: fetchPendingBills,
        updateBillStatus: updateBillStatus
    };
 
    return factory;
    
    function getTodaysCollection() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_REPORT+'collection/')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching reports');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllLabTests() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_LAB)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching LabTests');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllScanTests() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_SCAN)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching ScanTests');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllStaffDoctors() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_DOCTOR_STAFF)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Doctors');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllDoctors() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_DOCTOR)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Doctors');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function saveDoctor(doctor) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI_USER_DOCTOR, doctor)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while saving Doctor');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteDoctor(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI_USER_DOCTOR+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Doctor');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    

    function fetchAllPatients() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_PATIENT)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Patients');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function savePatient(patient) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI_USER_PATIENT, patient)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while saving Patient');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deletePatient(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI_USER_PATIENT+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Patient');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllBills() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_BILL)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Bills');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function saveBill(bill, billTests) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI_USER_BILL, {'type':bill.type,'amount':bill.amount, 'patient':{'id':bill.patient.id}, 'refDoctor':{'id':bill.refDoctor.id}, 'staffDoctor':{'id':bill.staffDoctor.id},'billTests':billTests})
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while saving Bill');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteBill(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI_USER_BILL+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Bill');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllBillTests() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_BILL_TEST)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching BillTests');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function saveBillTest(billTest) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI_USER_BILL_TEST, billTest)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while saving BillTest');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteBillTest(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI_USER_BILL_TEST+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting BillTest');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchPendingBills() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER_BILL_PENDING)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching PendingBills');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function updateBillStatus(id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI_USER_BILL_UPDATESTATUS+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating bill status');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);