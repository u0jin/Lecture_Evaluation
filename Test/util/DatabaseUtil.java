package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	// ���������� ���� �����ϴ� �κ�
	
	public static Connection getConnetion() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/TUTORIAL";
			String dbID = "root";
			String dbPassword = "5826";
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection(dbURL,dbID,dbPassword);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

}
