package frontend;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Authentication;
import backend.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField userfield;
	private JPasswordField passwordField;
	private JButton loginBtn;
	private JButton resetBtn;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Login frame = new Login();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headTxt = new JLabel("Hospital Management System");
		headTxt.setBounds(318, 45, 330, 32);
		headTxt.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		contentPane.add(headTxt);
		
		JLabel loginTxt = new JLabel("Staff Login");
		loginTxt.setFont(new Font("Tahoma", Font.PLAIN, 21));
		loginTxt.setHorizontalAlignment(SwingConstants.CENTER);
		loginTxt.setBounds(344, 104, 239, 32);
		contentPane.add(loginTxt);
		
		JLabel userlbl = new JLabel("UserName");
		userlbl.setHorizontalAlignment(SwingConstants.CENTER);
		userlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userlbl.setBounds(163, 190, 191, 38);
		contentPane.add(userlbl);
		
		userfield = new JTextField();
		userfield.setBounds(368, 190, 296, 38);
		contentPane.add(userfield);
		userfield.setColumns(10);
		
		JLabel passlbl = new JLabel("Password");
		passlbl.setHorizontalAlignment(SwingConstants.CENTER);
		passlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passlbl.setBounds(163, 265, 191, 38);
		contentPane.add(passlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(368, 269, 296, 38);
		contentPane.add(passwordField);
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginBtn.setBounds(526, 337, 144, 38);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(this);
		
		resetBtn = new JButton("Reset Password");
		resetBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resetBtn.setBounds(368, 337, 144, 38);
		contentPane.add(resetBtn);
		resetBtn.addActionListener(this);

	}
	

	
//    public boolean userLogin(String user, String psw) {
//    	PreparedStatement st;
//		String query = "SELECT * FROM staff WHERE `uname` = ? AND `password` = ?"; 
//		DbConnection connection = new DbConnection();
//		try {
//			st = DbConnection.conn.prepareStatement(query);
//			st.setString(1, user);
//			st.setString(2, psw);
//			ResultSet rs = st.executeQuery();
//			
//			if(rs.next()) {
//				return true;
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return false;
//	}  
            

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginBtn) {

			String username = userfield.getText();
            String password = String.valueOf(passwordField.getPassword());

            
            if(username.equals("") || password.equals("")) {
            	JOptionPane.showMessageDialog(this, "Username/Passowrd Field blank");
            }
            
            else {
            	this.dispose();
            	Authentication.login(username, password);
		}}
		if(e.getSource() == resetBtn) {
			userfield.setText("");
			passwordField.setText("");
		}
		
	}
}
