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

@WebServlet("/showdata")
public class ShowEmployeeServlet extends HttpServlet{
	private final static String query = "select id, fname, mname, lname, email, password, mobile, dob, designation from employee ";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get Printwriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		  
		
		//get the values
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///employeeapplication","root","root");
		 PreparedStatement ps = con.prepareStatement(query)	){
			//ResultSet
			ResultSet rs = ps.executeQuery();
			pw.println("<div style='margin:auto;width:900px;margin-top:100px;'>");
			pw.println("<a href='home.html'><button class='btn btn-primary text-center'>Employee Management Application</button></a>");
			pw.println("<h2 class='text-primary text-center'>Employees List</h2>");
			pw.println("<a href='home.html'><button class='btn btn-outline-success'>Add Employee</button></a>");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<th>ID</th>");
			pw.println("<th>fname</th>");			
			pw.println("<th>lname</th>");
			pw.println("<th>email</th>");
			pw.println("<th>designation</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getString(5)+"</td>");				
				pw.println("<td>"+rs.getString(9)+"</td>");
				pw.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Edit</a></td>");
				pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				pw.println("</tr>");
				
				
			}
			pw.println("</table>");
		} catch (SQLException e) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		pw.println("</div>");
		pw.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req, res);
	}
}
