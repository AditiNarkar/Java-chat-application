package application.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Client implements ActionListener
{
	
	static JFrame cf = new JFrame();
	JPanel pnlhead;
	JLabel lblusername, lblservericon, lblsend, lblstatus;
	JButton btnlogout, btnexit;
	JTextArea txtmsg;
	static JPanel txtbg;
	
	static Box verticalbox = Box.createVerticalBox();
	
	static Socket socket;
	static DataInputStream input;
	static DataOutputStream output;
	
	Client()
	{
		cf.setTitle("Client side");
		cf.setSize(450, 600);
		cf.setLocation(750, 80);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setVisible(true);
		
		cf.setLayout(new BorderLayout());
		
		try 
		{
			ImageIcon img_bg1 = new ImageIcon(ImageIO.read(new File("src/application/chat/images/bg6.jpg")));
			Image img_bg2 = img_bg1.getImage().getScaledInstance(450, 600, Image.SCALE_DEFAULT);
			ImageIcon img_bg3 = new ImageIcon(img_bg2);
			cf.setContentPane(new JLabel(img_bg3));
		} 
		catch (IOException e) {
			System.out.println(e);
		}
		
		pnlhead = new JPanel();
		pnlhead.setLayout(null);
		pnlhead.setBackground(new Color(0, 41, 3));
		pnlhead.setBounds(0, 0, 450, 60);
		cf.add(pnlhead);
		
		lblusername = new JLabel("Server");
		lblusername.setForeground(Color.WHITE);
		lblusername.setFont(new Font("Calibri", Font.BOLD, 20));
		lblusername.setBounds(95, 15, 200, 20);
		pnlhead.add(lblusername);
		
		lblstatus = new JLabel("Active");
		lblstatus.setForeground(Color.WHITE);
		lblstatus.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblstatus.setBounds(95, 35, 100, 20);
		pnlhead.add(lblstatus);
		
		btnlogout = new JButton("Log Out");
		btnlogout.setForeground(Color.white);
		btnlogout.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnlogout.setBackground(new Color(110, 0, 0));
		btnlogout.setBounds(250, 15, 90, 25);
		btnlogout.setFocusable(false);
		btnlogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlhead.add(btnlogout);
		btnlogout.addActionListener(this);
		
		btnexit = new JButton("Exit");
		btnexit.setForeground(Color.white);
		btnexit.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnexit.setBackground(new Color(110, 0, 0));
		btnexit.setBounds(350, 15, 80, 25);
		btnexit.setFocusable(false);
		btnexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlhead.add(btnexit);
		btnexit.addActionListener(this);
		
		txtbg = new JPanel();
		txtbg.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtbg.setBounds(10, 65, 420, 440);
		txtbg.setOpaque(false);
		//txtbg.setEditable(false);
		//txtbg.setLineWrap(true);
		//txtbg.setWrapStyleWord(true);
		cf.add(txtbg);
		
		txtmsg = new JTextArea();
		txtmsg.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtmsg.setBounds(10, 515, 360, 30);
		cf.add(txtmsg);
		
		//setVisible(true);
		
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
			cf.add(lblsend);
			
			lblsend.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me)
				{
					try 
					{
						String smsg = txtmsg.getText();
						output.writeUTF(smsg);
						
						txtmsg.setText("");
						
						JPanel pnlchatbg = bubbleMsg(smsg);
						txtbg.setLayout(new BorderLayout());
						
						JPanel pnlright = new JPanel(new BorderLayout());
						pnlright.add(pnlchatbg, BorderLayout.LINE_END);
						pnlright.setOpaque(false);
						verticalbox.add(pnlright);
						verticalbox.add(Box.createVerticalStrut(10));
						
						txtbg.add(verticalbox, BorderLayout.PAGE_START);
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
		
		cf.setVisible(true);
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
		lblmsg.setBackground(new Color(5, 41, 0));
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
			cf.dispose();
		}
		if(e.getSource() == btnexit)
		{
			cf.dispose();
		}
		
	}
	
	public static void main(String[] args) 
	{
		new Client().cf.setVisible(true);
		
		try
		{
			socket = new Socket("127.0.0.1", 5000);
			
			while(true)
			{
			input  = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			
			while(true)
			{
			String incomingmsg = "";
			incomingmsg = input.readUTF();
			//txtbg.setText(txtbg.getText() + "\n" + incomingmsg);
			JPanel pnlinput = bubbleMsg(incomingmsg);
			
			JPanel pnlleft = new JPanel(new BorderLayout());
			pnlleft.add(pnlinput, BorderLayout.LINE_START);
			pnlleft.setOpaque(false);
			verticalbox.add(pnlleft);
			verticalbox.add(Box.createVerticalStrut(10));
			cf.validate();
			}
			}
		}
		catch(Exception e) {System.out.println(e);}
	}
}