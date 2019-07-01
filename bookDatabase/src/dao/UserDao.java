package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.RequestDispatcher;

import controller.RegisterController;
import model.User;

public class UserDao implements Dao<User> {
	//This will be database instead of list
	private List<User> users = new ArrayList<>();  
//    public UserDao() {
//        users.add(new User("kullaniciadi", "sifre"));            
//  }
	@Override
	public Optional<User> get(long id) {
		//Anlamadim
		return Optional.ofNullable(users.get((int) id));
	}

	@Override
	public List<User> getAll() {
		//return all users in db
		return users;
	}

	@Override
	public void save(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		int isAdmin = user.isAdmin();
		try {
        	if (RegisterController.control(username, password, isAdmin)) {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db",
						"root", "");
				Statement st = conn.createStatement();
				String sql = "INSERT INTO `users` (`idusers`, `username`, `password`, `isAdmin`) VALUES (NULL, '"
						+ username + "', '" + password + "', '" + isAdmin + "')";
				ResultSet rs = st.executeQuery(sql);
				System.out.println("Data is Successfully Inserted into users Table");
//				return true;
			}
        	else {
//        		return false;
        		}
		} 
		catch (ClassNotFoundException | SQLException e) {
//			return false;
			}	
	}

	@Override
	public void update(User user, String[] params) {
		//This function will update user data in db
		user.setUsername(Objects.requireNonNull(
				params[0], "Name cannot be null"));
		user.setPassword(Objects.requireNonNull(
				params[1], "Password cannot be null"));
              users.remove(user); 
              users.add(user);				
	}

	@Override
	public void delete(User user) {
		//This function will delete user from db
		users.remove(user);		
	}
}
