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
				<a href=#report role=tab id=report-tab data-toggle=tab aria-controls=report>Reports</a>
			</li>
		</ul> 
		<div class=tab-content id=myTabContent> 
			
			<div class="tab-pane fade in active" role=tabpanel id=report aria-labelledby=report-tab> 
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