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
	<body ng-app="deviscan" class="ng-cloak" >
	<div class="generic-container" ng-controller="UserController as user">
	<div style="width:100%;">
		<img src="./images/logo_pic_text.png" style="padding-left:10px;height:40px;">
		<a href="/DEVISCAN/login"><img src="./images/logout.png" style="padding:5px 10px 5px 0px;float:right;width:40px;height:40px;"></a>
		<a href="#"><img src="./images/profile.png" style="padding:5px 10px 5px 0px;float:right;width:40px;height:40px;"></a>
		<span style="float:right;margin-right:10px;margin-top:10px;">Welcome ${loggedinUser.fullname},</span>
	</div>
	<div class="bs-example bs-example-tabs" data-example-id=togglable-tabs> 
		<ul class="nav nav-tabs" id=myTabs role=tablist> 
			<li role=presentation class=active>
				<a href=#billing role=tab id=billing-tab data-toggle=tab aria-controls=billing aria-expanded=true>Billing</a>
			</li>
			<li role=presentation>
				<a href=#report role=tab id=report-tab data-toggle=tab aria-controls=report>Reports</a>
			</li>
			<li role=presentation>
				<a href=#doctor id=doctor-tab role=tab data-toggle=tab aria-controls=doctor>Doctors</a>
			</li>
			<li role=presentation>
				<a href=#patient id=patient-tab role=tab data-toggle=tab aria-controls=patient>Patients</a>
			</li>
		</ul> 
		<div class=tab-content id=myTabContent> 
			<div class="tab-pane fade in active" role=tabpanel id=billing aria-labelledby=billing-tab> 
				<form ng-submit="user.submitBill()" name="newBill" class="form-horizontal">
				<table>
				<tr><td>
				<table style="width:800px;">
				<tr><td style="width:100%;"><div style="float:right;">Amount:<span ng-bind="user.bill.amount" style="margin-right:10px;"></span><button type="submit" class="btn btn-primary btn-sm" style="padding:0px;width:50px;height:30px;">Save
                              </button>
                              <button type="button" ng-click="user.resetBill()" class="btn btn-warning btn-sm" style="padding:0px;width:50px;height:30px;">
                              	Clear
                              </button></div></td></tr>
				</table>
				<table style="width:802px;" class="table table-hover table-bordered">
				<thead>
                          <tr>
                              <th style="width:200px;">Billing Type</th>
                              <th style="width:200px;">Patient</th>
                              <th style="width:200px;">Ref. Doctor</th>
                              <th style="width:200px;">Staff Doctor</th>
                          </tr>
                      	</thead>
                      	<tbody>
                      	<tr><td style="width:200px;height:35px;"><span ng-bind="user.bill.type"></span></td>
                      	<td style="width:200px;height:35px;"><span ng-bind="user.bill.patient.name"></span></td>
                      	<td style="width:200px;height:35px;"><span ng-bind="user.bill.refDoctor.name"></span></td>
                      	<td style="width:200px;height:35px;"><span ng-bind="user.bill.staffDoctor.name"></span></td></tr>
                <tr>
				<td style="width:200px;"><multiple-autocomplete ng-model="user.bill.type" object-property="name"
     					suggestions-arr="user.billingTypes" after-select-item="afterBillingTypeSelect">
					</multiple-autocomplete></td>
				<td style="width:200px;"><multiple-autocomplete ng-model="user.bill.patient.id" object-property="name"
     					suggestions-arr="user.patients" after-select-item="afterPatientSelect">
					</multiple-autocomplete></td>
				<td style="width:200px;"><multiple-autocomplete ng-model="user.bill.refDoctor.id" object-property="name"
     					suggestions-arr="user.doctors" after-select-item="afterRefDoctorSelect">
					</multiple-autocomplete></td>
				<td style="width:200px;"><multiple-autocomplete ng-model="user.bill.staffDoctor.id" object-property="name"
     					suggestions-arr="user.staffDoctors" after-select-item="afterStaffDoctorSelect">
					</multiple-autocomplete></td>
				</tr>
                      	</tbody>
				</table>
				<table class="table">
				<thead>
                          <tr>
                              <th style="width:200px;">Lab Tests</th>
                              <th style="width:200px;"></th>
                              <th style="width:200px;">Scans</th>
                              <th style="width:200px;"></th>
                          </tr>
                      	</thead>
                      	<tbody>
                      	<tr>
				<td style="width:200px;"><multiple-autocomplete ng-model="user.selectedLabTests" object-property="name"
     					suggestions-arr="user.labTests" after-select-item="afterLabSelectItem">
					</multiple-autocomplete></td>
					<td style="width:200px;">
					<table class="table table-hover table-bordered">
					<tr ng-repeat="lt in user.selectedLabTests"><td style="width:145px;height:37px;"><span>{{lt.name}}</span>(<span>{{lt.rate}}</span>)</td>
					<td style="width:37px;height:37px;"><button type="button" ng-click="user.removeLabTest(lt.id)" class="btn btn-danger" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/delete.png" width="16px" height="16px" />
                              </button></td></tr></table>
                    </td>
				<td style="width:200px;"><multiple-autocomplete ng-model="user.selectedScanTests" object-property="name"
     					suggestions-arr="user.scanTests" after-select-item="afterScanSelectItem">
					</multiple-autocomplete></td>
					<td style="width:200px;"><table class="table table-hover table-bordered">
					<tr ng-repeat="st in user.selectedScanTests"><td style="width:145px;height:37px;"><span>{{st.name}}</span>(<span>{{st.rate}}</span>)</td>
					<td style="width:37px;height:37px;"><button type="button" ng-click="user.removeScanTest(st.id)" class="btn btn-danger" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/delete.png" width="16px" height="16px" />
                              </button></td></tr></table></td>
				</tr>
                      	</tbody>
				
				</table>
				</td>
				<td >
				<table style="float:right;" class="table table-hover table-bordered">
					<tr ng-repeat="pb in user.pendingBills"><td style="width:145px;height:37px;"><span>{{pb.patient.name}}</span></td>
					<td style="width:75px;height:37px;"><button type="button" ng-click="user.updateBillStatus(pb.id)" class="btn" style="padding:0px;width:50px;height:25px;" >
                              	Done
                              </button></td></tr></table>
				</td></tr>
				</table>
				
				
				</form>
      		</div>
			<div class="tab-pane fade in" role=tabpanel id=report aria-labelledby=report-tab> 
				<form name="reportsForm" class="form-horizontal">
				
				<table>
				<tr><td><button type="button" ng-click="user.getTodaysCollection()" class="btn btn-primary btn-sm" style="padding:0px;width:150px;height:30px;">Today's Collection
                              </button>
                              </td>
                <td>Today's Collection: <span ng-bind="user.todaysCollection"></span></td>              
                </tr>
				</table>
				</form>
			</div>
		
			<div class="tab-pane fade in" role=tabpanel id=doctor aria-labelledby=doctor-tab> 
				
          <div class="panel panel-default" style="margin-bottom:0px;background-color:#DAE8E8;">
              <div class="formcontainer" style="padding:0px;">
                  <form ng-submit="user.submitDoctor()" name="newDoctor" class="form-horizontal">
                      <table class="table table-hover table-bordered">
                      	<thead>
                          <tr>
                              <th style="width:218px;">Name</th>
                              <th style="width:218px;">Address</th>
                              <th style="width:218px;">Mobile</th>
                              <th style="width:81px;"></th>
                          </tr>
                      	</thead>
                      	<tbody>
                          <tr>
                              <td><input type="hidden" ng-model="user.doctor.id" id="cId" />
                              <input type="text" ng-model="user.doctor.name" id="cName" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><input type="text" ng-model="user.doctor.address" id="cDetails" class="username form-control input-sm" style="width:200px;" /></td>
                              <td><input type="text" ng-model="user.doctor.mobile" id="cDetails" class="username form-control input-sm" style="width:200px;" /></td>
                              <td><button type="submit" class="btn btn-primary btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/save.png" width="25px" height="25px" />
                              </button>
                              <button type="button" ng-click="user.resetDoctor()" class="btn btn-warning btn-sm" style="padding:0px;width:30px;height:30px;">
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
                              <th style="width:218px;">Address</th>
                              <th style="width:218px;">Mobile</th>
                              <th style="width:47px;"></th>
                          </tr>
                      </thead>
                      <tbody style="height: 350px;overflow-y: auto;">
                          <tr ng-repeat="doctor in user.doctors">
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="doctor.name"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="doctor.address"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="doctor.mobile"></span></td>
                              <td style="padding:3px;">
                              <button type="button" ng-click="user.editDoctor(doctor.id)" class="btn btn-success" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/edit.png" width="16px" height="16px" />
                              </button>  
                              <button type="button" ng-click="user.removeDoctor(doctor.id)" class="btn btn-danger" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/delete.png" width="16px" height="16px" />
                              </button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <div class="tab-pane fade in" role=tabpanel id=patient aria-labelledby=patient-tab> 
				
          <div class="panel panel-default" style="margin-bottom:0px;background-color:#DAE8E8;">
              <div class="formcontainer" style="padding:0px;">
                  <form ng-submit="user.submitPatient()" name="newPatient" class="form-horizontal">
                      <table class="table table-hover table-bordered">
                      	<thead>
                          <tr>
                              <th style="width:218px;">Name</th>
                              <th style="width:218px;">Age</th>
                              <th style="width:218px;">Address</th>
                              <th style="width:218px;">Mobile</th>
                              <th style="width:138px;">Gender</th>
                              <th style="width:81px;"></th>
                          </tr>
                      	</thead>
                      	<tbody>
                          <tr>
                              <td><input type="hidden" ng-model="user.patient.id" id="cId" />
                              <input type="text" ng-model="user.patient.name" id="cName" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><input type="text" ng-model="user.patient.age" id="cName" class="username form-control input-sm" style="width:200px;" required /></td>
                              <td><input type="text" ng-model="user.patient.address" id="cDetails" class="username form-control input-sm" style="width:200px;" /></td>
                              <td><input type="text" ng-model="user.patient.mobile" id="cDetails" class="username form-control input-sm" style="width:200px;" /></td>
                              <td><select ng-model="user.patient.gender" id="dType" class="form-control input-sm" style="width:120px;" required>
                              	<option value="">Select Gender</option>
								<option ng-repeat="p in user.genderTypes" value="{{p.name}}">{{p.name}}</option></select></td>
                              <td><button type="submit" class="btn btn-primary btn-sm" style="padding:0px;width:30px;height:30px;">
                              	<img src="./images/save.png" width="25px" height="25px" />
                              </button>
                              <button type="button" ng-click="user.resetPatient()" class="btn btn-warning btn-sm" style="padding:0px;width:30px;height:30px;">
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
                              <th style="width:218px;">Age</th>
                              <th style="width:218px;">Address</th>
                              <th style="width:218px;">Mobile</th>
                              <th style="width:218px;">Gender</th>
                              <th style="width:47px;"></th>
                          </tr>
                      </thead>
                      <tbody style="height: 350px;overflow-y: auto;">
                          <tr ng-repeat="patient in user.patients">
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="patient.name"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="patient.age"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="patient.address"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="patient.mobile"></span></td>
                              <td style="padding:3px;width:218px;height:25px;"><span ng-bind="patient.gender"></span></td>
                              <td style="padding:3px;">
                              <button type="button" ng-click="user.editPatient(patient.id)" class="btn btn-success" style="padding:0px;width:18px;height:18px;" >
                              	<img src="./images/edit.png" width="16px" height="16px" />
                              </button>  
                              <button type="button" ng-click="user.removePatient(patient.id)" class="btn btn-danger" style="padding:0px;width:18px;height:18px;" >
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
      <script src="<c:url value='./js/user_service.js' />"></script>
      <script src="<c:url value='./js/user_controller.js' />"></script>
      <script src="./js/multiple-select.js"></script>
</body>
	<script src=./js/jquery-1.12.4.min.js>
	</script> <script>window.jQuery||document.write('<script src="./js/vendor/jquery.min.js"><\/script>')</script> 
	<script src=./js/bootstrap.min.js></script> 
	<script src=./js/docs.min.js></script> 
	<script src=./js/ie10-viewport-bug-workaround.js></script>
	<script src="./js/jquery-ui-1.12.1.js"></script>
</html>