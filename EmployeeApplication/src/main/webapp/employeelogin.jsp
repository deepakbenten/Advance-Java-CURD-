
<%
 if(session.getAttribute("email")==null){
	 response.sendRedirect("login.jsp");
 }
 %>

 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>employee-login</title>
</head>
<body>
<h2>This is employee page</h2>
<%=session.getAttribute("fname") %>
</body>
</html>