package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.User;

public class UserDaoImpl implements UserDao {
	// This will be database instead of list
	private List<User> users = new ArrayList<>();

//    public UserDao() {
//        users.add(new User("kullaniciadi", "sifre"));            
//  }

	@Override
	public boolean save(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
//		int isAdmin = user.isAdmin();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db", "root", "");

			String query = " INSERT INTO `users` (`idusers`, `username`, `password`, `isAdmin`) VALUES (NULL, ?, ?, 0)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
//					preparedStmt.setString(1, bookId);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.execute();
			System.out.println("Data is Successfully Inserted into users Table");
			return true;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public void update(User user, String[] params) {
		// This function will update user data in db
		user.setUsername(Objects.requireNonNull(params[0], "Name cannot be null"));
		user.setPassword(Objects.requireNonNull(params[1], "Password cannot be null"));
		users.remove(user);
		users.add(user);
	}

	@Override
	public void delete(User user) {
		// This function will delete user from db
		users.remove(user);
	}

	@Override
	public Boolean isUser(User user) {

		String username = user.getUsername();
		String password = user.getPassword();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db", "root", "");
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM `users` WHERE `username` = '" + username + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next() == true) {
				System.out.println("User " + username + " exist...!");
				sql = "SELECT * FROM `users` WHERE `username` = '" + username + "' AND `password` = '" + password + "'";
				rs = st.executeQuery(sql);
				if (rs.next() == true) {
					System.out.println("Login Success...!");
					return true;
				} else {
//					System.out.println("Wrong Password...!");
					System.out.println("Username or Password Wrong...!");
					return false;
				}
			} else {
//				System.out.println("User " + username + " does not exist...!");
				System.out.println("Username or Password Wrong...!");

				return false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("isUser catch");

			e.printStackTrace();
			return false;
		}

	}
}
