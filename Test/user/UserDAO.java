package user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DatabaseUtil;

// 데이터베이스와 실제 연결되는 , 직접접근
public class UserDAO {
	
	public int join(String userID, String userPassword) {
		String SQL = "INSERT INTO USER VALUES (?,?)";
		
		try {
			
			Connection conn = DatabaseUtil.getConnetion();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2,userPassword);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
