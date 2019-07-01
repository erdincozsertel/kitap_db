package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Book;

public class BookDaoImpl implements BookDao {

	public boolean isExist(Book book) {
		// TODO: will fix query
		// Book Controller
//		String bookId = book.getBookId().toString();
		String bookName = book.getBookName();
		String bookWriter = book.getBookWriter();
		String bookPublisher = book.getBookPublisher();
		String bookCategory = book.getBookCategory();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db", "root", "");
			// the mysql insert statement
			String query = " SELECT * FROM `books` WHERE bName = ? AND bWriter = ? AND bPublisher = ? AND category = ? ";
//			String query = " INSERT INTO `books` (`bId`, `bName`, `bWriter`, `bPublisher`, `category`) VALUES (NULL, ?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
//			preparedStmt.setString(1, bookId);
			preparedStmt.setString(1, bookName);
			preparedStmt.setString(2, bookWriter);
			preparedStmt.setString(3, bookPublisher);
			preparedStmt.setString(4, bookCategory);

			System.out.println(preparedStmt);

			// execute the preparedstatement
			preparedStmt.execute();
			System.out.println(preparedStmt.getResultSet());
			if (preparedStmt.getResultSet() != null) {
				System.out.println("Book Exist!");
				return true;
			} else {
				System.out.println("Book does not Exist!");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Book Exist catch!");
			System.out.println(e);
			return true;
		}
	}

	@Override
	public void save(Book book) {
//		String bookId = book.getBookId().toString();
		String bookName = book.getBookName();
		String bookWriter = book.getBookWriter();
		String bookPublisher = book.getBookPublisher();
		String bookCategory = book.getBookCategory();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db", "root", "");
			// the mysql insert statement

			String query = " INSERT INTO `books` (`bId`, `bName`, `bWriter`, `bPublisher`, `category`) VALUES (NULL, ?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
//				preparedStmt.setString(1, bookId);
			preparedStmt.setString(1, bookName);
			preparedStmt.setString(2, bookWriter);
			preparedStmt.setString(3, bookPublisher);
			preparedStmt.setString(4, bookCategory);
			preparedStmt.execute();

//				if (preparedStmt.getResultSet()!=null) {
			System.out.println("Data is Successfully Inserted into users Table");
//				} else {
//					System.out.println("Error!");
//				}

//				return true;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Book save catch");
			e.printStackTrace();
		}

	}

	@Override
	public void update(Book book, String[] params) {
		// TODO Update From database

	}

	@Override
	public void delete(Book book) {
		// TODO This function will delete user from db

	}

}
