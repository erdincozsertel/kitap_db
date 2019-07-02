package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean save(User user) {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			String query = " INSERT INTO `users` (`idusers`, `username`, `password`, `isAdmin`) VALUES (NULL, ?, ?, 0)";

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.execute();
			System.out.println("Data is Successfully Inserted into users Table");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}
		return false;
	}

	@Override
	public void update(User user, String[] params) {
		// TODO This function will update user data in db

	}

	@Override
	public void delete(User user) {
		// TODO This function will delete user from db
	}

	@Override
	public Boolean isUser(User user) {

		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		String username = user.getUsername();
		String password = user.getPassword();

		try {
			Statement st = connection.createStatement();
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
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}
		return false;
	}
}
