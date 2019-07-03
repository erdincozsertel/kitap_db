package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

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

		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		String bookName = book.getBookName();
		String bookWriter = book.getBookWriter();
		String bookPublisher = book.getBookPublisher();
		String bookCategory = book.getBookCategory();

		try {

			String query = " INSERT INTO `books` (`bId`, `bName`, `bWriter`, `bPublisher`, `category`) VALUES (NULL, ?, ?, ?, ?)";

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, bookName);
			preparedStmt.setString(2, bookWriter);
			preparedStmt.setString(3, bookPublisher);
			preparedStmt.setString(4, bookCategory);
			preparedStmt.execute();

			System.out.println("Data is Successfully Inserted into users Table");

		} catch (SQLException e) {
			System.out.println("Book save catch");
			e.printStackTrace();
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}

	}

	@Override
	public List<Book> getBookList() throws ServletException {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		try {
			String query = " SELECT * FROM `books` ";
			preparedStmt = connection.prepareStatement(query);

			ResultSet rs = preparedStmt.executeQuery(query);
			List<Book> list = new ArrayList<Book>();

			while (rs.next()) {
				Integer bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bWriter = rs.getString("bWriter");
				String bPublisher = rs.getString("bPublisher");
				String category = rs.getString("category");
				Book book = new Book(bId, bName, bWriter, bPublisher, category);
				list.add(book);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("An error occured while retrieving " + "all employees: " + e.toString());
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}
		return null;
	}

	@Override
	public void update(Book book) {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		String bookId = book.getBookId().toString();
		String bookName = book.getBookName();
		String bookWriter = book.getBookWriter();
		String bookPublisher = book.getBookPublisher();
		String bookCategory = book.getBookCategory();

		try {

			String query = " UPDATE `books` SET `bName` = ?, `bWriter` = ?, `bPublisher` = ?, `category` = ? WHERE `books`.`bId` = ?";
			preparedStmt = connection.prepareStatement(query);

			preparedStmt.setString(1, bookName);
			preparedStmt.setString(2, bookWriter);
			preparedStmt.setString(3, bookPublisher);
			preparedStmt.setString(4, bookCategory);
			preparedStmt.setString(5, bookId);
			preparedStmt.execute();

			System.out.println("Data is Successfully updated");

		} catch (SQLException e) {
			System.out.println("Book save catch");
			e.printStackTrace();
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}
	}

	@Override
	public void delete(Integer bookId) {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		System.out.println("ok");
		try {

			String query = " DELETE FROM `books` WHERE `bId`= ? ";

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setLong(1, bookId);

			preparedStmt.execute();

			System.out.println("Data is Successfully Deleted.");

		} catch (SQLException e) {
			System.out.println("Book delete catch");
			e.printStackTrace();
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}

	}

	@Override
	public Book getBook(Integer bookId) {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		String bId = bookId.toString(); 
		String bookName;
		String bookWriter;
		String bookPublisher;
		String bookCategory;
		Book book = null;
		try {

			String query = "SELECT * FROM `books` WHERE `bId` = ?";

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, bId);

			ResultSet rs=null;
			preparedStmt.execute();
			rs=preparedStmt.getResultSet();

//			while (rs.next()) {
				rs.next();
				bookName = rs.getString("bName");
				bookWriter = rs.getString("bWriter");
				bookPublisher = rs.getString("bPublisher");
				bookCategory = rs.getString("category");
				book = new Book(bookId, bookName, bookWriter, bookPublisher, bookCategory);
//			}
			System.out.println("Book is Successfully Found.");
			return book;

		} catch (SQLException e) {
			System.out.println("Book select catch");
			e.printStackTrace();
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}
		return null;
	}

}
