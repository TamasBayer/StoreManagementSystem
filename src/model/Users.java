package model;

public class Users {
	
	private String userName;
	private String userPassword;
	
	public Users(String uName, String uPassword) {
		this.userName = uName;
		this.userPassword = uPassword;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}
	
}
