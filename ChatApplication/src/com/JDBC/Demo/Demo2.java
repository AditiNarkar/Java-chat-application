package com.JDBC.Demo;

import java.sql.*;
class Demo2
{
	public static void main (String args[])
	{
		try
		{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			System.out. println(" Driver loaded");
			
			String url= "jdbc:mysql://localhost:3306/chatapp";
			String user = "root";
			String pwd = "271879";
			
			Connection cn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection to the database created");
			
			Statement st = cn.createStatement();
			String str   = "SELECT * FROM student";
			ResultSet rs = st.executeQuery(str);
			
			String text=" ";
			System.out.println("Roll Number \t Name");
			
			while(rs.next())
			{
				text= text + rs.getInt(1)+"\t" + rs.getString(2) + "\n";
			}
			System.out.print(text);
			
			st.close();
			cn.close();
		}
		catch (SQLException s)
		{
			System.out.println(s);
		}
	}
}