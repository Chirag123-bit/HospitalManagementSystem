package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import frontend.Admin_Panal;
import frontend.Doctor;

public class Authentication {
	
	
	  public static void login(String user, String psw) {
	    	PreparedStatement st;
			String query = "SELECT * FROM staff WHERE `uname` = ? AND `password` = ?"; 
			DbConnection connection = new DbConnection();
			try {
				st = DbConnection.conn.prepareStatement(query);
				st.setString(1, user);
				st.setString(2, psw);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					String post = rs.getString(5);
//					new Admin_Panal(rs.getInt(1));
					if(post.equals("admin")) {
						System.out.println("Admin");
						new Admin_Panal(rs.getInt(1)).setVisible(true);
					}
					else if(post.equals("doctor")) {
						new Doctor(rs.getInt(1)).setVisible(true);
					}
				}
				else {
					System.out.println("Not Logged");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}

}
