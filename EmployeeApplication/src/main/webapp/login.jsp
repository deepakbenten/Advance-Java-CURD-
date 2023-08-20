<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee-Login</title>
<link rel="stylesheet" href="css/bootstrap.css"></link>
</head>
<body class="container-fluid">
<div class="container mt-5">
<div class="card">
<form action="login" method="post" class="form card">
<div class="alert alert-primary" role="alert">
  Employee Login
</div>
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email">
   
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
  </div>
 
  <button type="submit" name="signin" id="signin" class="btn btn-primary">Login</button>
  &nbsp;&nbsp;&nbsp;&nbsp;
   <button type="submit" class="alert alert-primary"><a href="home.html">Register</a></button>
    &nbsp;&nbsp;&nbsp;&nbsp;
   <button type="submit" class="alert alert-primary"><a href="Admin.jsp">Admin Login</a></button>
</form>
</div>
</div>
</body>
</html>