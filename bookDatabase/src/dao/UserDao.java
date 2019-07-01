package dao;

import model.User;

public interface UserDao {
	
	boolean save (User user);
	
	void update (User user, String[] params);
	
	void delete(User user);
	
	Boolean isUser(User user);
	
	
	
}
