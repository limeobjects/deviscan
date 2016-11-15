'use strict';
 
angular.module('deviscan').factory('AdminService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI_ADMIN_CENTER = '/DEVISCAN/admin/center/';
    
    var REST_SERVICE_URI_ADMIN_USER = '/DEVISCAN/admin/user/';
    
    var REST_SERVICE_URI_ADMIN_DOCTOR = '/DEVISCAN/admin/doctor/';
    
    var REST_SERVICE_URI_ADMIN_TEST = '/DEVISCAN/admin/test/';
    
    var REST_SERVICE_URI_ADMIN_REPORT = '/DEVISCAN/admin/report/';
 
    var factory = {
        fetchAllCenters: fetchAllCenters,
        saveCenter: saveCenter,
        deleteCenter: deleteCenter,
        fetchAllUsers: fetchAllUsers,
        saveUser: saveUser,
        deleteUser: deleteUser,
        fetchAllDoctors: fetchAllDoctors,
        saveDoctor: saveDoctor,
        deleteDoctor: deleteDoctor,
        fetchAllTests: fetchAllTests,
        saveTest: saveTest,
        deleteTest: deleteTest,
        getTodaysCollection: getTodaysCollection
    };
 
    return factory;
    
    function getTodaysCollection() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_ADMIN_REPORT+'collection/')
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
    
    function fetchAllCenters() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_ADMIN_CENTER)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Centers');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function saveCenter(center) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI_ADMIN_CENTER, center)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while saving Center');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteCenter(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI_ADMIN_CENTER+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Center');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllDoctors() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_ADMIN_DOCTOR)
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
        $http.post(REST_SERVICE_URI_ADMIN_DOCTOR, doctor)
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
        $http.delete(REST_SERVICE_URI_ADMIN_DOCTOR+id)
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
    
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_ADMIN_USER)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function saveUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI_ADMIN_USER, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while saving User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteUser(username) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI_ADMIN_USER+username)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllTests() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_ADMIN_TEST)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Tests');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function saveTest(test) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI_ADMIN_TEST, test)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while saving Test');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteTest(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI_ADMIN_TEST+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Test');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);