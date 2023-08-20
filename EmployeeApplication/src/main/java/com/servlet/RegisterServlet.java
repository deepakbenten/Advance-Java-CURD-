package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.PreparableStatement;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private final static String query = "insert into employee(fname, mname, lname, email, password, mobile, dob, designation) values(?,?,?,?,?,?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get Printwriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		//get the values
		String fname= req.getParameter("fname");
		String mname= req.getParameter("mname");
		String lname= req.getParameter("lname");
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		String mobile= req.getParameter("mobile");
		String dob= req.getParameter("dob");
		String designation= req.getParameter("designation");
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
			ps.setString(2, mname);
			ps.setString(3, lname);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.setString(6, mobile);
			ps.setString(7, dob);
			ps.setString(8, designation);
			//execute the query
			int count = ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			if(count==1) {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Registered Successfully</h2>");
			}else {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Not Registered </h2>");
			}
			
		} catch (SQLException e) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
		pw.println("</div>");
		pw.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req, res);
	}
}
