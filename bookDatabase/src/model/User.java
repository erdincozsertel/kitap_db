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
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	String getUserName() {
		return this.username;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getIduser() {
		return iduser;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	
	
	

}
