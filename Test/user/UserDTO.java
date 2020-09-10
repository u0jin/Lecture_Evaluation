package user;

public class UserDTO {

	String userID;
	String userPassword;
	
	//데이터 가져오는거
	public String getUserID() {
		return userID;
	}
	// 데이터 기록하는거 
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
