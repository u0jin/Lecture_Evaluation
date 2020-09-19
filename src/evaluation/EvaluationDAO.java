package evaluation;

// ���� �򰡿� ���õ� ��� ���������� �����ϴ� ��ü

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;



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
	
	public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber) {

		if(lectureDivide.equals("��ü")) {

			lectureDivide = "";

		}

		ArrayList<EvaluationDTO> evaluationList = null;  // �����򰡱��� ����

		PreparedStatement pstmt = null;

		String SQL = "";

		try {

			if(searchType.equals("�ֽż�")) {

				SQL = "SELECT * FROM EVALUATION WHERE lectureDivide LIKE ? AND CONCAT(lectureName, professorName, evaluationTitle, evaluationContent) LIKE ? ORDER BY evaluationID DESC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;
// LIKE - mysql ���ڿ� -> Ư���� ���ڿ� �����ϴ���
			} else if(searchType.equals("��õ��")) {

				SQL = "SELECT * FROM EVALUATION WHERE lectureDivide LIKE ? AND CONCAT(lectureName, professorName, evaluationTitle, evaluationContent) LIKE ? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;

			}

			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, "%" + lectureDivide + "%");  // % : ���ڿ� ����

			pstmt.setString(2, "%" + search + "%");

			rs = pstmt.executeQuery();

			evaluationList = new ArrayList<EvaluationDTO>();

			while(rs.next()) {

				EvaluationDTO evaluation = new EvaluationDTO( // ����� ���ö����� �ʱ�ȯ

					rs.getInt(1),

					rs.getString(2),

					rs.getString(3),

					rs.getString(4),

					rs.getInt(5),

					rs.getString(6),

					rs.getString(7),

					rs.getString(8),

					rs.getString(9),

					rs.getString(10),

					rs.getString(11),

					rs.getString(12),

					rs.getString(13),

					rs.getInt(14)

				);

				evaluationList.add(evaluation);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if(rs != null) rs.close();

				if(pstmt != null) pstmt.close();

				if(conn != null) conn.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return evaluationList;
	}

}