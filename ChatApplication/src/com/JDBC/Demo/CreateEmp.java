package com.JDBC.Demo;

import java.sql.*;
public class CreateEmp {
	public static void main(String args[])
	{
		try
		{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			System.out.println("Driver loaded");
			
			String url = "jdbc:mysql://localhost:3306/chatapp";
			String user = "root";
			String pwd = "271879";
			
			Connection con = DriverManager.getConnection(url,user,pwd);
			Statement st = con.createStatement();
			//st.executeUpdate("CREATE TABLE emp (emp_id INT(5) ,emp_name VARCHAR(10))");
			
			st.executeUpdate("CREATE TABLE emp (emp_id INT(5) ,emp_name VARCHAR(10))");
			System.out.println("created");
			
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
