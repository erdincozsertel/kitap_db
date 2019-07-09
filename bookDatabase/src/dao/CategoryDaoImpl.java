package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import model.Category;

/**
 * Dao implementation class CategoryDaoImpl
 * @author erdincozsertel
 */
public class CategoryDaoImpl implements CategoryDao {

	@Override
	public boolean save(Category category) {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		String categoryName = category.getCategoryName();
		try {
			String query = " INSERT INTO `categories` (`categoryId`, `categoryName`) VALUES (NULL, ?)";

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, categoryName);
			preparedStmt.execute();
			System.out.println("Data is Successfully Inserted into category Table");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}
		return false;
	}

	@Override
	public void update(Category category, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Category category) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Category> getCategoryList() throws ServletException {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		try {
			String query = " SELECT * FROM `categories` ";
			preparedStmt = connection.prepareStatement(query);

			ResultSet rs = preparedStmt.executeQuery(query);
			List<Category> list = new ArrayList<Category>();

			while (rs.next()) {
				Integer categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				Category bCategory = new Category(categoryId, categoryName);
				list.add(bCategory);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("An error occured while retrieving " + "all categories: " + e.toString());
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}
		return null;
	}

	@Override
	public String getCategoryName(Integer categoryId) {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		try {
			String query = " SELECT `categoryName` FROM `categories` WHERE `categoryId` = ?";
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, categoryId.toString());

			ResultSet rs = preparedStmt.executeQuery();
			rs.next();

			String categoryName = rs.getString("categoryName");
			return categoryName;
		} catch (SQLException e) {
			System.out.println("An error occured while retrieving " + "all categories: " + e.toString());
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}
		return null;
	}

}
