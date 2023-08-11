package com.JDBC.Demo;

import java.sql.*;
class CreateInsertDemo 
{	public static void main(String args[])
	{
		//Connection con;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver registered successfully");
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		try
		{
			String url = "jdbc:mysql://localhost:3306/chatapp";
			String user = "root";
			String pwd = "271879";
			
			Connection con = DriverManager.getConnection(url,user,pwd);
			
			Statement st   = con.createStatement();
			st.executeUpdate("CREATE TABLE student (rollno INT(5) ,name VARCHAR(10))");
			System.out.println("Table created");
			
			Statement insertst   = con.createStatement();
			insertst.executeUpdate("INSERT INTO student VALUES (1,'aditi')");
			System.out.println("Record inserted");
			
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
}