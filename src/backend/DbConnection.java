package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	public static Connection conn;
	public DbConnection() {
		
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/hospital_management_system?" +
			"user=root&password=root");

			}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			}

			}
	}


