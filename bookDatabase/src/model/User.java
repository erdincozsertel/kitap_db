package model;

public class User {

	private Integer iduser;
	// this value will be auto generated by the database
	private String username;
	private String password;
	private Integer isAdmin;
	public enum Gender {MALE, FEMALE};
	private Gender gender;

	public User(String username, String password, Gender gender) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = 0;
		this.gender = gender;
	}
	
	

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

	public int isAdmin() {
		return isAdmin;
	}

	public void setAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIduser() {
		return iduser;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
