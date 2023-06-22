package com.servlet.register;

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


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    //create the query
    private static final String INSERT_QUERY = "INSERT into user(name, city, mobile, dob) VALUES (?,?,?,?);";

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		//set Content type
		res.setContentType("text/html");
		//read the form values
		String name = req.getParameter("name");
		String city = req.getParameter("city");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		
//		System.out.println("Name: "+name);
//		System.out.println("City: "+city);
//		System.out.println("Mobile: "+mobile);
//		System.out.println("dob: "+dob);
		
		//jdbc create connection point
//		String url = "jdbc:mysql://localhost:3306/db";
//		String user = "root";
//		String password = "Avanish@123";
		String Driver = "com.mysql.jdbc.Driver";
		
		//load the jdbc driver
		try {
			Class.forName(Driver);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//create connection
		try (
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root","Avanish@123");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
			//set the values
			ps.setString(1, name);
			ps.setString(2, city);
			ps.setString(3, mobile);
			ps.setString(4, dob);
			
			//execute query
			int count = ps.executeUpdate();
			if(count==0) {
				pw.print("record not stored into Database");
			}else {
				pw.print("record Stored into Database");
			}
			
		}catch (SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		}catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		
		//close the stream
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
