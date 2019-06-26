package model;

public class User {
	
	String iduser;
	String username;
	String password;
	boolean isAdmin;
	public User(String username, String password) {
		super();
		this.iduser = "NULL";
		this.username = username;
		this.password = password;
		this.isAdmin = false;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void getUserName() {
		
	}
	
	
	
	

}
