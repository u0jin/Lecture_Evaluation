package evaluation;

// ���� �򰡿� ���õ� ��� ���������� �����ϴ� ��ü

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;



public class EvaluationDAO {



	private Connection conn;

	private ResultSet rs;



	public EvaluationDAO() {

		try {

			String dbURL = "jdbc:mysql://localhost:3306/LectureEvaluation?useUnicode=true&characterEncoding=UTF-8";

			String dbID = "root";

			String dbPassword = "****";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	

	public int write(EvaluationDTO evaluationDTO) {
		// �۾��� �׸�
		// ����ڰ� �Ѱ��� ���������� ����Ҽ� �ֵ��� ����

		PreparedStatement pstmt = null;

		try {

			String SQL = "INSERT INTO EVALUATION VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0);";
			// ID ���� auto_increment�� �Ǿ��ֱ⶧����
			// NULL���� �־��ָ� ���ʷ� 1�� �����Ҽ�����
			// likey�� ����Ʈ���� 0��
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