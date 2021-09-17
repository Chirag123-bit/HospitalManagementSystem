package backend;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorOperations {
	static PreparedStatement st;
	static String query;
	static ResultSet res;
	DbConnection connection = new DbConnection();
	
	public static boolean setPrescription(String data, int id) {
		query= "UPDATE `patient` SET prescription=? WHERE id=?";
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

}
