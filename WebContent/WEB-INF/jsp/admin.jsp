<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty loggedinUser}">
<c:redirect url="/login"/>
</c:if>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="shortcut icon" type="image/png" href="./images/logo_pic_only.png"/>
		<title>Devi Scan</title>
		<link rel="stylesheet" type="text/css" href="./css/admin.css">
		<link href=./css/bootstrap.min.css rel=stylesheet> 
		<link href="data:text/css;charset=utf-8," rel=stylesheet data-href=./css/bootstrap-theme.min.css id=bs-theme-stylesheet> 
		<link href=./css/docs.min.css rel=stylesheet> 
		<script src=./js/ie-emulation-modes-warning.js></script>
		<link rel="stylesheet" href="./css/bootstrap-3.3.5.min.css">
     <link href="<c:url value='./css/app.css' />" rel="stylesheet"></link>
     <link rel="stylesheet" href="./css/jquery-ui-1.12.1.css">
     <link href="./css/multiple-select.css" rel="stylesheet">
	</head>
	<body ng-app="deviscan" class="ng-cloak">
	<div class="generic-container" ng-controller="AdminController as admin">
	<div style="width:100%;">
		<img src="./images/logo_pic_text.png" style="padding-left:10px;height:40px;">
		<a href="/DEVISCAN/login"><img src="./images/logout.png" style="padding:5px 10px 5px 0px;float:right;width:40px;height:40px;"></a>
		<a href="#"><img src="./images/profile.png" style="padding:5px 10px 5px 0px;float:right;width:40px;height:40px;"></a>
		<span style="float:right;margin-right:10px;margin-top:10px;">Welcome ${loggedinUser.fullname},</span>
	</div>
	<div class="bs-example bs-example-tabs" data-example-id=togglable-tabs> 
		<ul class="nav nav-tabs" id=myTabs role=tablist> 
			<li role=presentation class=active>
				<a href=#report role=tab id=report-tab data-toggle=tab aria-controls=report>Reports</a>
			</li>
			<li role=presentation>
				<a href=#user role=tab id=user-tab data-toggle=tab aria-controls=user>Users</a>
			</li>
			<li role=presentation>
				<a href=#doctor id=doctor-tab role=tab data-toggle=tab aria-controls=doctor aria-expanded=true>Doctors</a>
			</li>
			<li role=presentation>
				<a href=#center id=center-tab role=tab data-toggle=tab aria-controls=center aria-expanded=true>Centers</a>
			</li>
			<li role=presentation>
				<a href=#test id=test-tab role=tab data-toggle=tab aria-controls=test aria-expanded=true>Tests</a>
			</li>
		</ul> 
		<div class=tab-content id=myTabContent> 
			<div class="tab-pane fade in active" role=tabpanel id=report aria-labelledby=report-tab> 
			<form name="reportsForm" class="form-horizontal">
				
				<table>
				<tr><td><button type="button" ng-click="admin.getTodaysCollection()" class="btn btn-primary btn-sm" style="padding:0px;width:150px;height:30px;">Today's Collection
                              </button>
                              </td>
                <td><table><tr ng-repeat="cr in admin.collectionReports"><td><span ng-bind="cr.user.center.name"></span>:</td><td><span ng-bind="cr.amount"></span></td></tr></table></span></td>              
                </tr>
				</table>
				</form>
			</div>
		
			<div class="tab-pane fade in" role=tabpanel id=user aria-labelledby=user-tab> 
			
				<div class="panel panel-default" style="margin-bottom:0px;background-color:#DAE8E8;">
              <div class="formcontainer" style="padding:0px;">
                  <form ng-submit="admin.submitUser()" name="newUser" class="form-horizontal">
                      <table class="table table-hover table-bordered">
                      	<thead>
                          <tr>
                          	  <th style="width:218px;">Full Name</th>
                              <th style="width:218px;">Username</th>
                              <th style="width:218px;">Password</th>
                              <th style="width:138px;">Role</th>
                              <th style="width:138px;">Center</th>
                              <th style="width:81px;"></th>
                          </tr>
                      	</thead>
                      	<tbody>
                          <tr>
                              <td><input type="text" ng-model="admin.user.fullname" id="cfullname" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><input type="text" ng-model="admin.user.username" id="cusername" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><input type="password" ng-model="admin.user.password" id="cpassword" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><select ng-model="admin.user.role" id="role" class="form-control input-sm" style="width:120px;" required>
                              	<option value="">Select Role</option>
								<option ng-repeat="ut in admin.userTypes" value="{{ut.name}}">{{ut.name}}</option></select></td>
							  <td><select ng-model="admin.user.center.id" id="ucenter" class="form-control input-sm" style="width:120px;" required>
							  	<option value="">Select Center</option>
								<option ng-repeat="c in admin.centers" value="{{c.id}}">{{c.name}}</option></select></td>
                              <td><button type="submit" class="btn btn-primary btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/save.png" width="25px" height="25px" />
                              </button>
                              <button type="button" ng-click="admin.resetUser()" class="btn btn-warning btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/reset.png" width="25px" height="25px" />
                              </button>
                              </td>
                          </tr>
                      	</tbody>
                  	  </table>
                  </form>
              </div>
          </div>
          <div class="panel panel-default" style="margin-bottom:0px;height:400px;">
              <div class="tablecontainer" style="padding-left:0px;">
                  <table class="table table-hover table-condensed table-responsive table-striped table-bordered">
                      <thead>
                          <tr>
                              <th style="width:218px;">Full Name</th>
                              <th style="width:218px;">Username</th>
                              <th style="width:218px;">Role</th>
                              <th style="width:218px;">Center</th>
                              <th style="width:47px;"></th>
                          </tr>
                      </thead>
                      <tbody style="height: 350px;overflow-y: auto;">
                          <tr ng-repeat="user in admin.users">
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="user.fullname"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="user.username"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="user.role"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="user.center.name"></span></td>
                              <td style="padding:3px;">
                              <button type="button" ng-click="admin.editUser(user.username)" class="btn btn-success" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/edit.png" width="16px" height="16px" />
                              </button>  
                              <button type="button" ng-click="admin.removeUser(user.username)" class="btn btn-danger" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/delete.png" width="16px" height="16px" />
                              </button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
			
      		</div>	
      		
      		<div class="tab-pane fade in" role=tabpanel id=doctor aria-labelledby=doctor-tab> 
				
          <div class="panel panel-default" style="margin-bottom:0px;background-color:#DAE8E8;">
              <div class="formcontainer" style="padding:0px;">
                  <form ng-submit="admin.submitDoctor()" name="newDoctor" class="form-horizontal">
                      <table class="table table-hover table-bordered">
                      	<thead>
                          <tr>
                              <th style="width:218px;">Name</th>
                              <th style="width:138px;">Type</th>
                              <th style="width:218px;">Address</th>
                              <th style="width:218px;">Mobile</th>
                              <th style="width:81px;"></th>
                          </tr>
                      	</thead>
                      	<tbody>
                          <tr>
                              <td><input type="hidden" ng-model="admin.doctor.id" id="cId" />
                              <input type="text" ng-model="admin.doctor.name" id="cName" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><select ng-model="admin.doctor.type" id="dType" class="form-control input-sm" style="width:120px;" required>
                              	<option value="">Select Type</option>
								<option ng-repeat="dt in admin.doctorTypes" value="{{dt.name}}">{{dt.name}}</option></select></td>
                              <td><input type="text" ng-model="admin.doctor.address" id="cDetails" class="username form-control input-sm" style="width:200px;" /></td>
                              <td><input type="text" ng-model="admin.doctor.mobile" id="cDetails" class="username form-control input-sm" style="width:200px;" /></td>
                              <td><button type="submit" class="btn btn-primary btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/save.png" width="25px" height="25px" />
                              </button>
                              <button type="button" ng-click="admin.resetDoctor()" class="btn btn-warning btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/reset.png" width="25px" height="25px" />
                              </button>
                              </td>
                          </tr>
                      	</tbody>
                  	  </table>
                  </form>
              </div>
          </div>
          <div class="panel panel-default" style="margin-bottom:0px;height:400px;">
              <div class="tablecontainer" style="padding-left:0px;">
                  <table class="table table-hover table-condensed table-responsive table-striped table-bordered">
                      <thead>
                          <tr>
                              <th style="width:218px;">Name</th>
                              <th style="width:218px;">Type</th>
                              <th style="width:218px;">Address</th>
                              <th style="width:218px;">Mobile</th>
                              <th style="width:47px;"></th>
                          </tr>
                      </thead>
                      <tbody style="height: 350px;overflow-y: auto;">
                          <tr ng-repeat="doctor in admin.doctors">
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="doctor.name"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="doctor.type"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="doctor.address"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="doctor.mobile"></span></td>
                              <td style="padding:3px;">
                              <button type="button" ng-click="admin.editDoctor(doctor.id)" class="btn btn-success" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/edit.png" width="16px" height="16px" />
                              </button>  
                              <button type="button" ng-click="admin.removeDoctor(doctor.id)" class="btn btn-danger" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/delete.png" width="16px" height="16px" />
                              </button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div> 
      		
			<div class="tab-pane fade in" role=tabpanel id=center aria-labelledby=center-tab> 
				
          <div class="panel panel-default" style="margin-bottom:0px;background-color:#DAE8E8;">
              <div class="formcontainer" style="padding:0px;">
                  <form ng-submit="admin.submitCenter()" name="newCenter" class="form-horizontal">
                      <table class="table table-hover table-bordered">
                      	<thead>
                          <tr>
                              <th style="width:218px;">Name</th>
                              <th style="width:218px;">Details</th>
                              <th style="width:81px;"></th>
                          </tr>
                      	</thead>
                      	<tbody>
                          <tr>
                              <td><input type="hidden" ng-model="admin.center.id" id="cId" />
                              <input type="text" ng-model="admin.center.name" id="cName" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><input type="text" ng-model="admin.center.details" id="cDetails" class="username form-control input-sm" style="width:200px;" /></td>
                              <td><button type="submit" class="btn btn-primary btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/save.png" width="25px" height="25px" />
                              </button>
                              <button type="button" ng-click="admin.resetCenter()" class="btn btn-warning btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/reset.png" width="25px" height="25px" />
                              </button>
                              </td>
                          </tr>
                      	</tbody>
                  	  </table>
                  </form>
              </div>
          </div>
          <div class="panel panel-default" style="margin-bottom:0px;height:400px;">
              <div class="tablecontainer" style="padding-left:0px;">
                  <table class="table table-hover table-condensed table-responsive table-striped table-bordered">
                      <thead>
                          <tr>
                              <th style="width:218px;">Name</th>
                              <th style="width:218px;">Details</th>
                              <th style="width:47px;"></th>
                          </tr>
                      </thead>
                      <tbody style="height: 350px;overflow-y: auto;">
                          <tr ng-repeat="center in admin.centers">
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="center.name"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="center.details"></span></td>
                              <td style="padding:3px;">
                              <button type="button" ng-click="admin.editCenter(center.id)" class="btn btn-success" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/edit.png" width="16px" height="16px" />
                              </button>  
                              <button type="button" ng-click="admin.removeCenter(center.id)" class="btn btn-danger" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/delete.png" width="16px" height="16px" />
                              </button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div> 
      
      <div class="tab-pane fade in" role=tabpanel id=test aria-labelledby=test-tab> 
				
          <div class="panel panel-default" style="margin-bottom:0px;background-color:#DAE8E8;">
              <div class="formcontainer" style="padding:0px;">
                  <form ng-submit="admin.submitTest()" name="newTest" class="form-horizontal">
                      <table class="table table-hover table-bordered">
                      	<thead>
                          <tr>
                              <th style="width:138px;">Type</th>
                              <th style="width:218px;">Name</th>
                              <th style="width:118px;">Rate</th>
                              <th style="width:118px;">JSSK</th>
                              <th style="width:118px;">RSBY</th>
                              <th style="width:118px;">RBSK</th>
                              <th style="width:118px;">TRIBAL</th>
                              <th style="width:81px;"></th>
                          </tr>
                      	</thead>
                      	<tbody>
                          <tr>
                              <td><select ng-model="admin.test.type" id="dType" class="form-control input-sm" style="width:120px;" required>
                              	<option value="">Select Type</option>
								<option ng-repeat="tt in admin.testTypes" value="{{tt.name}}">{{tt.name}}</option></select></td>
                              <td><input type="hidden" ng-model="admin.test.id" id="cId" />
                              <input type="text" ng-model="admin.test.name" id="cName" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><input type="text" ng-model="admin.test.rate" id="cDetails" class="username form-control input-sm" style="width:100px;" /></td>
                              <td><input type="text" ng-model="admin.test.jssk" id="cDetails" class="username form-control input-sm" style="width:100px;" /></td>
                              <td><input type="text" ng-model="admin.test.rsby" id="cDetails" class="username form-control input-sm" style="width:100px;" /></td>
                              <td><input type="text" ng-model="admin.test.rbsk" id="cDetails" class="username form-control input-sm" style="width:100px;" /></td>
                              <td><input type="text" ng-model="admin.test.tribal" id="cDetails" class="username form-control input-sm" style="width:100px;" /></td>
                              <td><button type="submit" class="btn btn-primary btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/save.png" width="25px" height="25px" />
                              </button>
                              <button type="button" ng-click="admin.resetTest()" class="btn btn-warning btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/reset.png" width="25px" height="25px" />
                              </button>
                              </td>
                          </tr>
                      	</tbody>
                  	  </table>
                  </form>
              </div>
          </div>
          <div class="panel panel-default" style="margin-bottom:0px;height:400px;">
              <div class="tablecontainer" style="padding-left:0px;">
                  <table class="table table-hover table-condensed table-responsive table-striped table-bordered">
                      <thead>
                          <tr>
                              <th style="width:138px;">Type</th>
                              <th style="width:218px;">Name</th>
                              <th style="width:118px;">Rate</th>
                              <th style="width:118px;">JSSK</th>
                              <th style="width:118px;">RSBY</th>
                              <th style="width:118px;">RBSK</th>
                              <th style="width:118px;">TRIBAL</th>
                              <th style="width:47px;"></th>
                          </tr>
                      </thead>
                      <tbody style="height: 350px;overflow-y: auto;">
                          <tr ng-repeat="test in admin.tests">
                              <td style="padding:3px;width:138px;height:25px;"><span ng-bind="test.type"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="test.name"></span></td>
                              <td style="padding:3px;width:118px;height:25px;"><span ng-bind="test.rate"></span></td>
                              <td style="padding:3px;width:118px;height:25px;"><span ng-bind="test.jssk"></span></td>
                              <td style="padding:3px;width:118px;height:25px;"><span ng-bind="test.rsby"></span></td>
                              <td style="padding:3px;width:118px;height:25px;"><span ng-bind="test.rbsk"></span></td>
                              <td style="padding:3px;width:118px;height:25px;"><span ng-bind="test.tribal"></span></td>
                              <td style="padding:3px;">
                              <button type="button" ng-click="admin.editTest(test.id)" class="btn btn-success" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/edit.png" width="16px" height="16px" />
                              </button>  
                              <button type="button" ng-click="admin.removeTest(test.id)" class="btn btn-danger" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/delete.png" width="16px" height="16px" />
                              </button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div> 
      
      
			</div>
		</div> 
	</div>
	
	  <script src="./js/angular-1.4.4.js"></script>
      <script src="<c:url value='./js/app.js' />"></script>
      <script src="<c:url value='./js/admin_service.js' />"></script>
      <script src="<c:url value='./js/admin_controller.js' />"></script>
      <script src="./js/multiple-select.js"></script>
</body>
	<script src=./js/jquery-1.12.4.min.js>
	</script> <script>window.jQuery||document.write('<script src="./js/vendor/jquery.min.js"><\/script>')</script> 
	<script src=./js/bootstrap.min.js></script> 
	<script src=./js/docs.min.js></script> 
	<script src=./js/ie10-viewport-bug-workaround.js></script>
	<script src="./js/jquery-ui-1.12.1.js"></script>
</html>