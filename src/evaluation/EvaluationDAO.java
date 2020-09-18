package evaluation;

// 강의 평가와 관련된 디비에 적접적으로 접근하는 객체

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;



public class EvaluationDAO {



	private Connection conn;

	private ResultSet rs;



	public EvaluationDAO() {

		try {

			String dbURL = "jdbc:mysql://localhost:3306/Lecture";

			String dbID = "root";

			String dbPassword = "5826";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	

	public int write(EvaluationDTO evaluationDTO) {
		// 글쓰기 항목
		// 사용자가 한개의 강의정보를 기록할수 있도록 해줌

		PreparedStatement pstmt = null;

		try {

			String SQL = "INSERT INTO EVALUATION VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0);";
			// ID 값이 auto_increment로 되어있기때문에
			// NULL값을 넣어주면 차례로 1씩 증가할수있음
			// likey는 디폴트값이 0임
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, evaluationDTO.getUserID());

			pstmt.setString(2, evaluationDTO.getLectureName());

			pstmt.setString(3, evaluationDTO.getProfessorName());

			pstmt.setInt(4, evaluationDTO.getLectureYear());

			pstmt.setString(5, evaluationDTO.getSemesterDivide());

			pstmt.setString(6, evaluationDTO.getLectureDivide());

			pstmt.setString(7, evaluationDTO.getEvaluationTitle());

			pstmt.setString(8, evaluationDTO.getEvaluationContent());

			pstmt.setString(9, evaluationDTO.getTotalScore());

			pstmt.setString(10, evaluationDTO.getCreditScore());

			pstmt.setString(11, evaluationDTO.getComfortableScore());

			pstmt.setString(12, evaluationDTO.getLectureScore());

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if(pstmt != null) pstmt.close();

				if(conn != null) conn.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return -1;

	}

}