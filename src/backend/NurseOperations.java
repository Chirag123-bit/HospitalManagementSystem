package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NurseOperations {
	static PreparedStatement st;
	static String query;
	static ResultSet res;
	DbConnection connection = new DbConnection();
	
	public static ResultSet getPatients(int staff_id) {
		query = "select * from patient where nurse=?" ;
	
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, staff_id);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static ResultSet getWardDetails(int pat_id) {
		query = "select distinct wd.name as ward_name, bd.bed as bed_name from admit_request ad, ward wd, bed as bd, patient as pt where bd.patient=? and wd.idward = ad.ward;" ;
		
		try {
			st = DbConnection.conn.prepareStatement(query);
			st.setInt(1, pat_id);
			res = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
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

}
