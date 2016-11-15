<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" type="image/png" href="./images/logo_pic_only.png"/>
<title>Medmart India</title>
<link rel="stylesheet" type="text/css" href="./css/login.css">
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
</head>
<body>
<form:form method="POST" name="loginForm" action="/MEDMART/authenticate" modelAttribute="authentication">
<div class="login-form">
	 <div class="form-group ">
     	<img src="./images/logo_pic_text.png" style="margin-left:60px;">
     </div>
     <div class="form-group ">
       <form:input type="text" class="form-control" path="username" placeholder="Username " id="UserName" /> 
       <i class="fa fa-user"></i>
     </div>
     <div class="form-group log-status">
       <form:input type="password" class="form-control" path="password" placeholder="Password" id="Passwod" />
       <i class="fa fa-lock"></i>
     </div>
      <span class="alert">Invalid Credentials</span>
     <button type="submit" class="log-btn" >Log in</button>
   </div>
   </form:form>
   <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
   <script src="./js/login.js"></script>
</body>
</html>