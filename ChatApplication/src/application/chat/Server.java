package application.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Server implements ActionListener
{
	JPanel pnlhead;
	JLabel lblusername, lblservericon, lblsend, lblstatus, lblservermsg;
	JButton btnlogout;
	JTextArea txtservermsg;
	JPanel txtserverbg;
	static JFrame f = new JFrame();
	
	static Box verticalbox = Box.createVerticalBox();
	
	static ServerSocket server;
	static Socket socket;
	
	static DataInputStream input;
	static DataOutputStream output;
	
	Server()
	{
		f.setTitle("Server side");
		f.setSize(450, 600);
		f.setLocation(220, 80);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		f.setLayout(new BorderLayout());
		
		try 
		{
			ImageIcon img_bg1 = new ImageIcon(ImageIO.read(new File("src/application/chat/images/bg4.jpg")));
			Image img_bg2 = img_bg1.getImage().getScaledInstance(450, 600, Image.SCALE_DEFAULT);
			ImageIcon img_bg3 = new ImageIcon(img_bg2);
			f.setContentPane(new JLabel(img_bg3));
		} 
		catch (IOException e) {
			System.out.println(e);
		}
		
		pnlhead = new JPanel();
		pnlhead.setLayout(null);
		pnlhead.setBackground(new Color(0, 23, 59));
		pnlhead.setBounds(0, 0, 450, 60);
		f.add(pnlhead);
		
		lblusername = new JLabel("Client");
		lblusername.setForeground(Color.WHITE);
		lblusername.setFont(new Font("Calibri", Font.BOLD, 20));
		lblusername.setBounds(95, 15, 200, 20);
		pnlhead.add(lblusername);
		
		lblstatus = new JLabel("Active");
		lblstatus.setForeground(Color.WHITE);
		lblstatus.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblstatus.setBounds(95, 35, 100, 20);
		pnlhead.add(lblstatus);
		
		btnlogout = new JButton("Leave");
		btnlogout.setForeground(Color.white);
		btnlogout.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnlogout.setBackground(new Color(110, 0, 0));
		btnlogout.setBounds(340, 15, 70, 25);
		btnlogout.setFocusable(false);
		btnlogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlhead.add(btnlogout);
		btnlogout.addActionListener(this);
		
		txtserverbg = new JPanel();
		txtserverbg.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtserverbg.setBounds(10, 65, 420, 440);
		txtserverbg.setOpaque(false);
		//txtserverbg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		//txtserverbg.setEditable(false);
		//txtserverbg.setLineWrap(true);
		//txtserverbg.setWrapStyleWord(true);
		f.add(txtserverbg);
		
		txtservermsg = new JTextArea();
		txtservermsg.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtservermsg.setBounds(10, 515, 360, 25);
		f.add(txtservermsg);
		
		
		try
		{
			ImageIcon img_user1 = new ImageIcon(ImageIO.read(new File("src/application/chat/images/profile3.png")));
			Image img_user2 = img_user1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
			ImageIcon img_user3 = new ImageIcon(img_user2);
			lblservericon = new JLabel(img_user3);
			lblservericon.setBounds(25,2, 60, 60);
			pnlhead.add(lblservericon);
			
			ImageIcon img_send1 = new ImageIcon(ImageIO.read(new File("src/application/chat/images/send2.png")));
			Image img_send2 = img_send1.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT);
			ImageIcon img_send3 = new ImageIcon(img_send2);
			lblsend = new JLabel(img_send3);
			lblsend.setBounds(380,505, 50,50);
			lblsend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			f.add(lblsend);
			
			lblsend.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me)
				{
					try 
					{
						String smsg = txtservermsg.getText();
						output.writeUTF(smsg);
						
						txtservermsg.setText("");
						
						JPanel pnlchatbg = bubbleMsg(smsg);
						txtserverbg.setLayout(new BorderLayout());
						
						JPanel pnlright = new JPanel(new BorderLayout());
						pnlright.add(pnlchatbg, BorderLayout.LINE_END);
						pnlright.setOpaque(false);
						verticalbox.add(pnlright);
						verticalbox.add(Box.createVerticalStrut(10));
						
						txtserverbg.add(verticalbox, BorderLayout.PAGE_START);
						//txtserverbg.add(pnlchatbg);
						
					}
					
					catch (Exception e4) 
					{ 
						System.out.println("e4 "+ e4); 
					}
				}

			});
		}
		catch(Exception e2)
		{
			System.out.println(e2);
		}
		
		f.setVisible(true);
	}
	
	public static JPanel bubbleMsg(String msg)
	{
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("HH:MM:SS");
		//JLabel lbldate = new JLabel(date.format(c.getTime()));
		
		
		JLabel lblmsg = new JLabel("<html><p style = \"width: 150px\">" + msg + "<br><div style = \"font-size: 6px\"; >" + date.format(c.getTime()) + "</div></p></html>");
		lblmsg.setBackground(new Color(69, 0, 61));
		lblmsg.setForeground(Color.WHITE);
		lblmsg.setBorder(new EmptyBorder(5,5,5,5));
		lblmsg.setOpaque(true);
		
		p.add(lblmsg);
		//p.add(lbldate);
		return p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnlogout)
		{
			f.dispose();
		}
		
	}
	
	public static void main(String[] args) 
	{
		new Server().f.setVisible(true);
		
		try 
		{
			server = new ServerSocket(5000);
			
			while(true)
			{
				socket = server.accept();
				
				output = new DataOutputStream(socket.getOutputStream());
				input = new DataInputStream(socket.getInputStream());
				
				while(true) 
				{
					String incomingmsg = "";
					incomingmsg = input.readUTF();
					JPanel pnlinput = bubbleMsg(incomingmsg);
					
					JPanel pnlleft = new JPanel(new BorderLayout());
					pnlleft.add(pnlinput, BorderLayout.LINE_START);
					pnlleft.setOpaque(false);
					verticalbox.add(pnlleft);
					verticalbox.add(Box.createVerticalStrut(10));
					f.validate();
				}
			}
			/*socket.close();
			server.close();*/
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
	}
}
