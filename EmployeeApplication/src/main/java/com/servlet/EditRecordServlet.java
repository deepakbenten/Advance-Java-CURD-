package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditRecordServlet extends HttpServlet{
private final static String query = "update employee set fname=?,  lname=?, email=?,  designation=? where id=? ";
	

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//get Printwriter
	PrintWriter pw = res.getWriter();
	
	//set content type
	res.setContentType("text/html");
	//link the bootstrap
	pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
	//get the values
	int id = Integer.parseInt(req.getParameter("id"));
	String fname=req.getParameter("fname");
	String lname=req.getParameter("lname");
	String email=req.getParameter("email");
	String designation=req.getParameter("designation");
	//load the JDBC driver
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	//generate the connection
	try(Connection con = DriverManager.getConnection("jdbc:mysql:///employeeapplication","root","root");
	 PreparedStatement ps = con.prepareStatement(query)	){
		//set the values
		
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.setString(3, email);
		ps.setString(4, designation);
		ps.setInt(5, id);
		//execute the query
		int count = ps.executeUpdate();
		pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
		if(count==1) {
			pw.println("<h2 class='bg-danger text-light text-center'>Record Edit Successfully</h2>");
		}else {
			pw.println("<h2 class='bg-danger text-light text-center'>Record Not Edit  </h2>");
		}
		
	} catch (SQLException e) {
		pw.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
		e.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
	pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
	pw.println("&nbsp; &nbsp;");
	pw.println("<a href='showdata'><button class='btn btn-outline-success'>Show Employee</button></a>");
	pw.println("</div>");
	pw.close();
	
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 doGet(req, res);
}
}
