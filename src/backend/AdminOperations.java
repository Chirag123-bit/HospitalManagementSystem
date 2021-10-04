package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminOperations {
	static PreparedStatement st;
	static String query;
	static ResultSet res;
	DbConnection connection = new DbConnection();

	public static boolean updatePatient(String up_fname,String up_lname,String up_Dob,String up_admit_date,String up_med_history,int up_doc,int up_nurse,String up_desp, int id) {
		query= "UPDATE `patient` SET fname=?, lname=?, dob=?, admit_date=?, medical_history=?, doctor=?, nurse=?, description=? WHERE id=?";
	
		try {
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
	        if(st.executeUpdate()>0) {return true;}
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static ResultSet getDoctors() {
		query = "SELECT * FROM staff where `post` = 'doctor' ";
		try {
			DbConnection connection = new DbConnection();
			st = DbConnection.conn.prepareStatement(query);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public static ResultSet getNurses() {
		query = "SELECT * FROM staff where `post` = 'nurse' ";
		try {
			st = DbConnection.conn.prepareStatement(query);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public static ResultSet getPatients() {
		query = "select * from patient" ;
		try {
			st = DbConnection.conn.prepareStatement(query);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean register(String fname, String lname, String uname, String post, String password) {
		query = "INSERT INTO `staff`(`fname`,`lname`,`uname`,`post`,`password`) VALUES(?, ?, ?, ?, ?)"; 
		
		try {
			DbConnection connection = new DbConnection();
			st = DbConnection.conn.prepareStatement(query);
			st.setString(1, fname);
			st.setString(2, lname);
			st.setString(3, uname);
			st.setString(4, post);
			st.setString(5, password);
			if(st.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	public static boolean addPatient(String fname,String lname,String history,int assignedDoctor,int assignedNurse,String desc,String dob,String dateAdmitted) {
		query = "INSERT INTO `patient`(`fname`,`lname`,`medical_history`,`description`,`doctor`,`nurse`,`dob`,`admit_date`) VALUES(?, ?, ?, ?, ?,?,?,?);";
		try {
			DbConnection connection = new DbConnection();
			st = DbConnection.conn.prepareStatement(query);
			st.setString(1, fname);
			st.setString(2, lname);
			st.setString(3, history);
			st.setString(4, desc);
			st.setInt(5, assignedDoctor);
			st.setInt(6, assignedNurse);
			st.setString(7, dob);
			st.setString(8, dateAdmitted);
			if(st.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static ResultSet getRequests() {
		query = "select idadmit_request, p.fname as pname, wd.name as ward,wd.idward as wid, s.fname as doc_name, ad.bed as bed, p.id as pid  from admit_request ad, patient p, ward wd, staff s WHERE p.status = 'Pending' AND ad.patient = p.id AND ad.requested_by = s.idstaff AND ad.ward = wd.idward" ;
		try {
			st = DbConnection.conn.prepareStatement(query);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean uploadReport(int id, byte[] data) {
		query = "INSERT INTO `lab`(`patient`,`report`) VALUES(?, ?);";
		try {
			DbConnection connection = new DbConnection();
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, id);
			st.setBytes(2, data);
			if(st.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static ResultSet getWards() {
		query = "SELECT * FROM ward";
		try {
			DbConnection connection = new DbConnection();
			st = DbConnection.conn.prepareStatement(query);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public static boolean addBed(int ward, String name) {
		query = "INSERT INTO `bed`(`ward`,`bed`) VALUES(?, ?)"; 
		
		try {
			DbConnection connection = new DbConnection();
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, ward);
			st.setString(2, name);
			if(st.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	public static ResultSet getAvaliableBeds(int id) {
		query = "SELECT * FROM bed where ward=? and status=?";
		try {
			DbConnection connection = new DbConnection();
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, id);
			st.setString(2, "Avaliable");
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	public static ResultSet getAllocatedBeds() {
		query = "select pt.fname, pt.lname, wd.name, bd.bed from ward as wd, patient as pt, bed as bd where bd.patient is not null and pt.id = bd.patient and bd.ward = wd.idward;";
		try {
			DbConnection connection = new DbConnection();
			st = DbConnection.conn.prepareStatement(query);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public static boolean admitPatient(int bed, int admit_id, int userID) {
		 String query = "UPDATE `admit_request` SET bed=? WHERE idadmit_request=?";
         String queryAdmit= "UPDATE `patient` SET status='Admitted' WHERE id=?";
         String queryDone= "UPDATE `bed` SET status='Allocated', patient=? WHERE idbed=?";
         
         try {
 			DbConnection connection = new DbConnection();
 			st = DbConnection.conn.prepareStatement(query);
 			st.setInt(1, bed);
	        st.setInt(2, admit_id);
	        st.executeUpdate();
	        
	        st = DbConnection.conn.prepareStatement(queryAdmit);
 			st.setInt(1, userID);
	        st.executeUpdate();
	        
	        st = DbConnection.conn.prepareStatement(queryDone);
	        st.setInt(1, userID);
 			st.setInt(2, bed);
 			if(st.executeUpdate()>0) {
 				return true;
 			}
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
        
        return false;

	}



}
