package com.Networking.Demo;

import java.net.InetAddress;

public class DatagramDemo1 
{
	public static void main (String args[])
	{
		try
		{
			InetAddress ip = InetAddress.getByName("localhost");
			System.out.println("Host: " + ip.getHostName());
			System.out.println("IP Address: " + ip.getHostAddress());
		}
		catch(Exception e) {}
	}
}
