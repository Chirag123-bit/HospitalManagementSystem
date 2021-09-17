package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class RequestAdmit extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField userField;
	private JTextField wardField;
	private JComboBox wardBox;
	private JButton btnRequest;
	private static int user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestAdmit frame = new RequestAdmit(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RequestAdmit(int user) {
		this.user = user;
		System.out.println(user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel patLabel = new JLabel("Admit Patient");
		patLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		patLabel.setHorizontalAlignment(SwingConstants.CENTER);
		patLabel.setBounds(195, 10, 240, 31);
		contentPane.add(patLabel);
		
		JLabel idLabel = new JLabel("Patient Id");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		idLabel.setBounds(54, 68, 122, 31);
		contentPane.add(idLabel);
		
		userField = new JTextField();
		userField.setText(Integer.toString(RequestAdmit.user));
		userField.setEnabled(false);
		userField.setBounds(215, 67, 229, 35);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ward");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(54, 122, 122, 31);
		contentPane.add(lblNewLabel_1_1);
		
		wardBox = new JComboBox();
		wardBox.setBounds(215, 121, 229, 35);
		contentPane.add(wardBox);
		
		wardField = new JTextField();
		wardField.setEnabled(false);
		wardField.setColumns(10);
		wardField.setBounds(215, 121, 229, 35);
		contentPane.add(wardField);
		
		btnRequest = new JButton("Request Ward");
		btnRequest.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRequest.setBounds(225, 166, 178, 43);
		contentPane.add(btnRequest);
		btnRequest.addActionListener(this);
		
		populateCombo();

	}
	
	public void populateCombo() {
		PreparedStatement st;
		DbConnection connection = new DbConnection();
		String query = "SELECT * FROM ward ";
		try {
			st = DbConnection.conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				wardBox.addItem(new ComboItems(res.getString(2), res.getInt(1)));

				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRequest) {
			Object item = wardBox.getSelectedItem();
			int ward = ((ComboItems) item).getValue();
			
			PreparedStatement st;
			String query = "INSERT INTO `admit_request` (`patient`,`ward`) VALUES(?, ?)";
			try {
				st = DbConnection.conn.prepareStatement(query);
				st.setInt(1, user);
				st.setInt(2, ward);
				
				if(st.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null, "Admit Request Sent Successfully");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
