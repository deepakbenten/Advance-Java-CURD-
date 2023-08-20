package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final static String query="select email from admin where email=? and password=?";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		
		
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
					ps.setString(1, email);
					ps.setString(2, password);
					
					//resultset
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						session.setAttribute("email", rs.getString("email"));
							dispatcher = request.getRequestDispatcher("showdata");
					}else {
						request.setAttribute("status", "failed");
						 dispatcher = request.getRequestDispatcher("login.jsp");
					}
					
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
		
		
	}


}
