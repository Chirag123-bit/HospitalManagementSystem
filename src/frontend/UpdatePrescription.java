package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.TimePicker;

import backend.DoctorOperations;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class UpdatePrescription extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textMed;
	private TimePicker timePicker;
	private JButton btnAdd;
	private static int id;
	private static String time;
	private static String pres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePrescription frame = new UpdatePrescription(id, pres, time);
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
	public UpdatePrescription(int id, String pres, String time) {
		this.id = id;
		this.pres = pres;
		this.time = time;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Prescription");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(93, 10, 262, 31);
		contentPane.add(lblNewLabel);
		
		JLabel medLbl = new JLabel("Medicine");
		medLbl.setHorizontalAlignment(SwingConstants.CENTER);
		medLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		medLbl.setBounds(10, 68, 159, 31);
		contentPane.add(medLbl);
		
		textMed = new JTextField();
		textMed.setBounds(167, 68, 236, 31);
		contentPane.add(textMed);
		textMed.setColumns(10);
		textMed.setText(pres);
		
		JLabel timeLbl = new JLabel("Time");
		timeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		timeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timeLbl.setBounds(10, 123, 159, 31);
		contentPane.add(timeLbl);
		
		timePicker = new TimePicker();
		timePicker.setBounds(167, 130, 236, 31);
		contentPane.add(timePicker);
		timePicker.setText(time);

		
		btnAdd = new JButton("Update Prescription");
		btnAdd.setBounds(182, 187, 151, 38);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd) {
			if(DoctorOperations.updatePresctiption(textMed.getText(), timePicker.getText(), id)) {
				JOptionPane.showMessageDialog(null, "Prescription Updated Successfully");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed to Update Prescription");
			}
		}
		
	}
}
