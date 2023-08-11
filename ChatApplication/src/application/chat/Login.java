package application.chat;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;

public class Login
{
	public static void main(String[] args) 
	{
		new LoginProg();
	}
}
class LoginProg extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JLabel lbluser, lblbg, lbllogin, lblusername, lblpwd;
	JTextField txtemail;
	JPasswordField txtpwd;
	JButton btnlogin;
	LoginProg() 
	{
		setTitle("Login Page");
		setSize(450, 600);
		setLocation(400, 70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(89, 0, 67));
		
		try
		{
			setLayout(new BorderLayout());
			
			ImageIcon img_bg1 = new ImageIcon(ImageIO.read(new File("src/application/chat/images/bg3.jpg")));
			Image img_bg2 = img_bg1.getImage().getScaledInstance(450, 600, Image.SCALE_DEFAULT);
			ImageIcon img_bg3 = new ImageIcon(img_bg2);
			setContentPane(new JLabel(img_bg3));
			
			setLayout(null);
			
			ImageIcon img_user1 = new ImageIcon(ImageIO.read(new File("src/application/chat/images/profile2.png")));
			Image img_user2 = img_user1.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
			ImageIcon img_user3 = new ImageIcon(img_user2);
			lbluser = new JLabel(img_user3);
			lbluser.setBounds(180, 100, 70,70);
			add(lbluser);
			
		}
		catch(IOException e) 
		{ 
			System.out.println(e); 
		}
		
		setLayout(null);
		
		lbllogin = new JLabel("Enter Registered Credentials");
		lbllogin.setForeground(Color.WHITE);
		lbllogin.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lbllogin.setBounds(100, 150, 250, 100);
		add(lbllogin);
		
		lblusername = new JLabel("Username");
		lblusername.setForeground(Color.WHITE);
		lblusername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblusername.setBounds(110, 200, 100, 100);
		add(lblusername);
		
		txtemail = new JTextField(10);
		txtemail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtemail.setBounds(110, 265, 210, 25);
		add(txtemail);
		
		lblpwd = new JLabel("Password");
		lblpwd.setForeground(Color.WHITE);
		lblpwd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblpwd.setBounds(110, 260, 250, 100);
		add(lblpwd);
		
		txtpwd = new JPasswordField(10);
		txtpwd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtpwd.setBounds(110, 320, 210, 25);
		add(txtpwd);
		
		btnlogin = new JButton("Login");
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setBackground(Color.BLACK);
		btnlogin.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		btnlogin.setBounds(180, 370, 70, 30);
		btnlogin.setFocusable(false);
		add(btnlogin);
		btnlogin.addActionListener(this);
		setVisible(true);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e7)
		{
			System.out.println(e7);
		}
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == btnlogin)
		{
			String username = txtemail.getText();
			String pwd		= txtpwd.getText();
			
			String url   = "jdbc:mysql://localhost:3306/chatapp";
			String user  = "root";
			String dbpwd = "";
			
			try 
			{
				Connection con = DriverManager.getConnection(url,user,dbpwd);
				
				PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM clients WHERE client_username=? AND client_password=?");
				ps.setString(1,username);
				ps.setString(2,pwd);
				ResultSet rs = ps.executeQuery();
				
				int n = 0;
				while(rs.next())
				{
					n = Integer.parseInt(rs.getString(1));
				}
				
				if(n!=0)
				{
					//System.out.println("Logged in successfully");
					JOptionPane.showMessageDialog(null,"Logged in successfully");
				}
				else
					JOptionPane.showMessageDialog(null,"Invalid credentials.");
	
				ps.close();
				con.close();
			} 
			catch (Exception e5) 
			{
				System.out.println(e5);
			}
			
		}
	}
}

