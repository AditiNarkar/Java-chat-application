package com.Networking.Demo;


import java.net.*;
public class DgramSender
{
	public static void main(String[] args) throws Exception
	{
		DatagramSocket ds = new DatagramSocket(); String str = "Java is Easy!!!!!";
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 5050);
		ds.send(dp);
		ds.close();
	} 
}