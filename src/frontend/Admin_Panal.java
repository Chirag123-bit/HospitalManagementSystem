package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import backend.DbConnection;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class Admin_Panal extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTable table;
	private JTextField fname;
	private JTextField lname;
	private JTextField dob;
	private JTextField dateAdmitted;
	private JTextField med_history;
	private JComboBox<String> doctorCombo;
	private JComboBox<String> nurseCombo;
	private JTextArea desc;
	private JButton updateDetails;
	ArrayList<Object> doctorList = new ArrayList<>();
	ArrayList <Object>nurseList = new ArrayList<>();
	ArrayList <Integer>patientList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Panal frame = new Admin_Panal();
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
	public Admin_Panal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 703);
		getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Hospital Administration");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		titleLabel.setBounds(367, 20, 350, 33);
		getContentPane().add(titleLabel);
		
		JButton btnNewButton = new JButton("Add Patient");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(76, 98, 181, 54);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Admit Patient");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(324, 98, 181, 54);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Add Lab Report");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(579, 98, 181, 54);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBounds(857, 98, 181, 54);
		getContentPane().add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(367, 189, 715, 369);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"First Name", "Last Name", "DOB", "Admit Date", "Med_History", "Doctor", "Nurse", "Description"
			}
		));
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(27, 192, 75, 25);
		getContentPane().add(lblNewLabel);
		
		fname = new JTextField();
		fname.setBounds(112, 193, 198, 25);
		getContentPane().add(fname);
		fname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(27, 227, 75, 25);
		getContentPane().add(lblNewLabel_1);
		
		lname = new JTextField();
		lname.setColumns(10);
		lname.setBounds(112, 228, 198, 25);
		getContentPane().add(lname);
		
		JLabel lblNewLabel_2 = new JLabel(" DOB");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(27, 272, 75, 25);
		getContentPane().add(lblNewLabel_2);
		
		dob = new JTextField();
		dob.setColumns(10);
		dob.setBounds(112, 273, 198, 25);
		getContentPane().add(dob);
		
		JLabel lblNewLabel_2_1 = new JLabel("Date Admitted");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(11, 307, 91, 25);
		getContentPane().add(lblNewLabel_2_1);
		
		dateAdmitted = new JTextField();
		dateAdmitted.setColumns(10);
		dateAdmitted.setBounds(112, 308, 198, 25);
		getContentPane().add(dateAdmitted);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Medical History");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBounds(11, 352, 91, 25);
		getContentPane().add(lblNewLabel_2_1_1);
		
		med_history = new JTextField();
		med_history.setColumns(10);
		med_history.setBounds(112, 353, 198, 25);
		getContentPane().add(med_history);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Doctor");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_2.setBounds(27, 401, 75, 25);
		getContentPane().add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Description");
		lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_3.setBounds(11, 511, 75, 25);
		getContentPane().add(lblNewLabel_2_1_3);
		
		desc = new JTextArea();
		desc.setBounds(112, 489, 245, 69);
		getContentPane().add(desc);
		
		updateDetails = new JButton("Update Details");
		updateDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		updateDetails.setBounds(132, 568, 181, 54);
		getContentPane().add(updateDetails);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("Nurse");
		lblNewLabel_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_2_1.setBounds(27, 449, 75, 25);
		getContentPane().add(lblNewLabel_2_1_2_1);
		
		doctorCombo = new JComboBox<String>();
		doctorCombo.setBounds(112, 404, 198, 22);
		getContentPane().add(doctorCombo);
		
		nurseCombo = new JComboBox<String>();
		nurseCombo.setBounds(112, 452, 198, 22);
		getContentPane().add(nurseCombo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(null);
		
		populatePatientSection();
		populateComboBox();
	}


	
	private void populatePatientSection() {
		PreparedStatement st;
		int colCount;
		DbConnection connection = new DbConnection();
		try {
	        
	        String query = "select * from patient" ;
	        st = DbConnection.conn.prepareStatement(query);
	         ResultSet rs = st.executeQuery();
	        ResultSetMetaData  patientData= (ResultSetMetaData) rs.getMetaData();
	        
	        colCount = patientData.getColumnCount();
	        
	        DefaultTableModel recordtable=(DefaultTableModel)table.getModel();
	        recordtable.setRowCount(0);
	                
	        while (rs.next()) {
	                    
                Vector<String> columnData= new Vector<String>();
                patientList.add(rs.getInt("id"));
                
                for(int i=0;i<=colCount;i++) {
                    columnData.add(rs.getString("fname"));
                    columnData.add(rs.getString("lname"));
                    columnData.add(rs.getString("dob"));
                    columnData.add(rs.getString("admit_date"));
                    columnData.add(rs.getString("medical_history"));
                    columnData.add(rs.getString("doctor"));
                    columnData.add(rs.getString("nurse"));
                    columnData.add(rs.getString("desc"));
                    
                    
                }
                recordtable.addRow(columnData);  
                System.out.println(patientList);
            }
	        
	                
		}
	    catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, ex);
	        
	        
	        
	        
	    }
	}
	public void populateComboBox() {
		PreparedStatement st;
		PreparedStatement st2;
		DbConnection connection = new DbConnection();
		String query = "SELECT * FROM staff where `post` = 'doctor' ";
		String query2 = "SELECT * FROM staff where `post` = 'nurse' ";
		try {
			st = DbConnection.conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				doctorList.add(res.getString(1));
				doctorCombo.addItem(res.getString(2));
				
				
			}
			st2 = DbConnection.conn.prepareStatement(query2);
			ResultSet res2 = st2.executeQuery();
			while(res2.next()) {
				nurseList.add(res2.getString(1));
				nurseCombo.addItem(res2.getString(2));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		 DefaultTableModel recordtable= (DefaultTableModel)table.getModel();
	        int SelectedRow=table.getSelectedRow();
	        fname.setText(recordtable.getValueAt(SelectedRow,0).toString());
	        lname.setText(recordtable.getValueAt(SelectedRow,1).toString());
	        dob.setText(recordtable.getValueAt(SelectedRow,2).toString());
	        dateAdmitted.setText(recordtable.getValueAt(SelectedRow,3).toString());
	        med_history.setText(recordtable.getValueAt(SelectedRow,4).toString());
	        desc.setText(recordtable.getValueAt(SelectedRow,7).toString());
//	        txtmedicalhistory.setText(recordtable.getValueAt(SelectedRow,5).toString());

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==updateDetails){
			
	        String up_fname, up_lname, up_Dob, up_admit_date, up_med_history,up_desp;
	        Integer  up_doc, up_nurse, id;
	        up_fname = fname.getText();
	        up_lname = lname.getText();
	        up_Dob = dob.getText();
	        up_admit_date = dateAdmitted.getText();
	        up_med_history = med_history.getText();
	        up_doc = (int) doctorList.get(table.getSelectedRow());
	        up_nurse = (int) nurseList.get(table.getSelectedRow());
	        up_desp = desc.getText();
	        id= (int) patientList.get(table.getSelectedRow());
	        
	    
	        String query= "update patient set fname=?,lname=?,dob=?,admit_date=?,medical_history=?,doctor=?,nurse=?,desc=? where id=?";
	        PreparedStatement st;
	        try {
	        	DbConnection connection = new DbConnection();
	        	st = DbConnection.conn.prepareStatement(query);
	        
		        st.setString(1, up_fname);
		        st.setString(2, up_lname);
		        st.setString(3, up_Dob);
		        st.setString(4, up_admit_date);
		        st.setString(5, up_med_history);
		        st.setInt(6, up_doc);
		        st.setInt(7, up_nurse);
		        st.setString(8, up_desp);
		        st.setInt(9, id);
	    
	        
	        if(st.executeUpdate()>0) {
		        JOptionPane.showMessageDialog(null, "data updated successsfully");
//		        update_db(); // Create this method
	        	}
	        } 
	        catch (SQLException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	        }
	        }
			
		}
		
	}
	
	

