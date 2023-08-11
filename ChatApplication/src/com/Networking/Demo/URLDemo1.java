package com.Networking.Demo;

import java.net.*;

public class URLDemo1 {

	public static void main(String[] args) throws MalformedURLException
	{
		URL p = new URL("https://www.javatpoint.com/javafix-tutorial");
		System.out.println(p.getProtocol());
		System.out.println(p.getPort());
		System.out.println(p.getHost());
		System.out.println(p.getFile());
	}

}
