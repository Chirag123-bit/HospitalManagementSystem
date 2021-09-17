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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import backend.DbConnection;

import javax.swing.JTextArea;
import javax.swing.JButton;


public class Doctor extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private ArrayList<Integer> patientList = new ArrayList<>();
	private static int docId;
	private JTextArea presArea;
	private JTextArea diagArea;
	private int SelectedRow;
	private JButton presBtn;
	private JButton diagBtn;
	private JButton wardBtn;
	private int id;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor frame = new Doctor(docId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Doctor(int id) {
		this.docId = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1195, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Doctor Panal");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(492, 23, 505, 43);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(325, 245, 846, 287);
		contentPane.add(scrollPane_1);
		
		scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		model = new DefaultTableModel();
		table = new JTable();
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		table.setModel(model);
		scrollPane.setViewportView(table);
		Object[] column = {
				"First Name", "Last Name", "DOB", "Admit Date", "Med_History",  "Description","Ward Status","Prescription","Diagnosis"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);

		diagArea = new JTextArea();
		diagArea.setBounds(21, 414, 284, 95);
		contentPane.add(diagArea);
		
		JLabel lblDiag = new JLabel("Current Diagnosis");
		lblDiag.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiag.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiag.setBounds(49, 377, 210, 27);
		contentPane.add(lblDiag);
		
		diagBtn = new JButton("Add Diagnosis");
		diagBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		diagBtn.setBounds(72, 514, 165, 34);
		contentPane.add(diagBtn);
		diagBtn.addActionListener(this);
		
		JLabel lblPatient = new JLabel("My Patients");
		lblPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatient.setBounds(636, 208, 217, 27);
		contentPane.add(lblPatient);
		
		presArea = new JTextArea();
		presArea.setBounds(24, 211, 263, 112);
		contentPane.add(presArea);
		
		presBtn = new JButton("Add Prescriptions");
		presBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		presBtn.setBounds(75, 333, 165, 34);
		contentPane.add(presBtn);
		presBtn.addActionListener(this);
		
		JLabel lblPresc = new JLabel("Current Prescriptions");
		lblPresc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPresc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPresc.setBounds(49, 174, 210, 27);
		contentPane.add(lblPresc);
		
		wardBtn = new JButton("Request ward");
		wardBtn.setBounds(380, 91, 198, 49);
		contentPane.add(wardBtn);
		wardBtn.addActionListener(this);
		
		JButton labBtn = new JButton("Show Lab Report");
		labBtn.setBounds(655, 91, 198, 49);
		contentPane.add(labBtn);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.setBounds(957, 91, 198, 49);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel_1 = new JLabel("Chirag Simkhada");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(21, 52, 232, 34);
		contentPane.add(lblNewLabel_1);
		populatePatientSection();
		table.removeColumn(table.getColumnModel().getColumn(8));
		table.removeColumn(table.getColumnModel().getColumn(7));
		


	}
	
	
	private void populatePatientSection() {
		PreparedStatement st;
		int colCount;
		DbConnection connection = new DbConnection();
		try {
	        
	        String query = "select * from patient where doctor=3" ;
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
                    columnData.add(rs.getString("description"));
                    columnData.add(rs.getString("status"));
                    columnData.add(rs.getString("diagnosis"));
                    columnData.add(rs.getString("prescription"));
                   

                    
                    
                }
                recordtable.addRow(columnData);  
            }
	        
	                
		}
	    catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, ex);
	        
	        
	        
	        
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String query="a";
		String data = "None";
		if(e.getSource() == presBtn || e.getSource() == diagBtn) {
			if(e.getSource() == presBtn) {
				query= "UPDATE `patient` SET prescription=? WHERE id=?";
				data = presArea.getText();}
			else {
				query= "UPDATE `patient` SET diagnosis=? WHERE id=?";
				data = diagArea.getText();
				}
			query= "UPDATE `patient` SET diagnosis=? WHERE id=?";
			data = diagArea.getText();


	        PreparedStatement st;
	        try {
	        	DbConnection connection = new DbConnection();
	        	st = DbConnection.conn.prepareStatement(query);
	        
		        st.setString(1, data);
		        st.setInt(2, id);
	    
	        
	        if(st.executeUpdate()>0) {
		        JOptionPane.showMessageDialog(null, "data updated successsfully");
		        refreshTable();
		        
	        	}
	        } 
	        catch (SQLException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
		}

		
		
        }
		else if(e.getSource() == wardBtn) {
			new RequestAdmit(id).setVisible(true);;
			
		}
	}
		
		

	public void refreshTable() {
		for(int i=model.getRowCount()-1;i>=0;i--){
			model.removeRow(i);
		    }
		populatePatientSection();
		}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		id=  patientList.get(table.getSelectedRow());
		int[] selRows = table.getSelectedRows();
		TableModel recordtable= table.getModel();
        SelectedRow=table.getSelectedRow();
        presArea.setText(recordtable.getValueAt(selRows[0],8).toString());
        diagArea.setText(recordtable.getValueAt(selRows[0],7).toString());
		
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
}
