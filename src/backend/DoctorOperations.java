package backend;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorOperations {
	static PreparedStatement st;
	static String query;
	static ResultSet res;
	DbConnection connection = new DbConnection();
	
	public static boolean setPrescription(String data, String time, int id) {
		query= "INSERT INTO `prescription`(medicine, time, patient) VALUES(?,?,?)";
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setString(1, data);
			st.setString(2, time);
	        st.setInt(3, id);
	        if(st.executeUpdate()>0) {
	        	return true;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean setDiagnosis(String data, int id) {
		query= "UPDATE `patient` SET diagnosis=? WHERE id=?";
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setString(1, data);
			st.setInt(2, id);
			if(st.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static ResultSet getPatients(int docId) {
		query = "select * from patient where doctor=?" ;
		
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, docId);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public static ResultSet getDates(int patId) {
		query = "select * from key_treatment_date where patient=?" ;
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, patId);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean setTreatment(String data, int id) {
		query= "Insert into `key_treatment_date`(`Treatment`,`patient`) VALUES(?, ?)";
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setString(1, data);
	        st.setInt(2, id);
	        if(st.executeUpdate()>0) {
	        	return true;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public static boolean removeTreatment(int id) {
		query= "delete from key_treatment_date where idkey_treatment_date = ? ";
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, id);
			if(st.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean updateTreatment(String data, int treatId, String date, String time, String lab) {
		query= "UPDATE `key_treatment_date` SET Date=?, Treatment=?, lab=?, Time=? WHERE idkey_treatment_date=?";
		
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setString(1, time);
			st.setString(2, data);
			st.setString(3, lab);
			st.setString(4, date);
	        st.setInt(5, treatId);
	        if(st.executeUpdate()>0) {
	        	return true;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static ResultSet getMedicines(int patId) {
		query = "select * from prescription where patient=?" ;
		
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, patId);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public static boolean updatePresctiption(String data,String time, int id) {
		query= "UPDATE `prescription` SET medicine=?, time=? WHERE id=?";
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setString(1, data);
			st.setString(2, time);
			st.setInt(3, id);
			if(st.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean removePresctiption(int id) {
		query= "delete from `prescription` WHERE id=?";
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, id);
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
