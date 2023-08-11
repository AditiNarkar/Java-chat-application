# Java-chat-application

Made in - Second Year Diploma (IT)

**Java Socket programming** - Java Socket programming is used for communication between the applications running on different JRE. Java Socket programming can be connection-oriented or connection-less.
Socket and ServerSocket classes are used for connection-oriented socket programming and DatagramSocket and DatagramPacket classes are used for connection-less socket programming.
The client in socket programming must know:
1.	IP Address of Server
2.	Port number

Here, one-way client and server communication is made. In this application, client sends a message to the server, server reads the message and prints it in server screen. Here, two classes are being used: Socket and ServerSocket. The Socket class is used to communicate client and server. Through this class, we can read and write message. The ServerSocket class is used at server-side. The accept() method of ServerSocket class blocks the console until the client is connected. After the successful connection of client, it returns the instance of Socket at server-side.

## Software Used
1.	JDK (Java Development Kit) - Version 14.0.1
2.	MySQL Workbench	- Version 8.0 CE
3.	MySQL Installer – Community	Version 8.0.27
4.	Eclipse IDE for Java Editors - Version 14.16 (2021-06)
5.	MySQL connector .jar file - Version 8.0.27 (Included in project)

## Installation

- Open project folder in editor.
- Port used is '5000'.
- MySQL database has username as 'root' and password as null. Start MySQL server.
- Start Server file first then Client file.

## Output
Login page

<img src="https://drive.google.com/uc?export=view&id=1MO7R6TbjaIT7cZf4l2RR_Ch2L71TAQbS" style="width: 400px; max-width: 100%; height: auto" />

Server 

<img src="https://drive.google.com/uc?export=view&id=12O_LaUdUiGWDGXTV0bv5gLAVYg-KPB3s" style="width: 400px; max-width: 100%; height: auto" />

Client 

<img src="https://drive.google.com/uc?export=view&id=1uQDgQDlWV51RJM_YMv7SWTSChMN1amW2" style="width: 400px; max-width: 100%; height: auto" />