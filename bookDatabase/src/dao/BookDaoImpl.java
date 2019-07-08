package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import model.Book;
import model.Category;

public class BookDaoImpl implements BookDao {

	/*
	 * public boolean isExist(Book book) { // TODO: will fix query // Book
	 * Controller // String bookId = book.getBookId().toString(); String bookName =
	 * book.getBookName(); String bookWriter = book.getBookWriter(); String
	 * bookPublisher = book.getBookPublisher(); Integer bookCategory =
	 * book.getBookCategory().getCategoryId();
	 * 
	 * try { Class.forName("com.mysql.jdbc.Driver"); Connection conn =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db",
	 * "root", ""); // the mysql insert statement String query =
	 * " SELECT * FROM `books` WHERE bName = ? AND bWriter = ? AND bPublisher = ? AND bCategory = ? "
	 * ;
	 * 
	 * // create the mysql insert preparedstatement PreparedStatement preparedStmt =
	 * conn.prepareStatement(query); // preparedStmt.setString(1, bookId);
	 * preparedStmt.setString(1, bookName); preparedStmt.setString(2, bookWriter);
	 * preparedStmt.setString(3, bookPublisher); preparedStmt.setString(4,
	 * bookCategory.toString());
	 * 
	 * System.out.println(preparedStmt);
	 * 
	 * // execute the preparedstatement preparedStmt.execute();
	 * System.out.println(preparedStmt.getResultSet()); if
	 * (preparedStmt.getResultSet() != null) { System.out.println("Book Exist!");
	 * return true; } else { System.out.println("Book does not Exist!"); return
	 * false; }
	 * 
	 * } catch (Exception e) { System.out.println("Book Exist catch!");
	 * System.out.println(e); return true; } }
	 */

	@Override
	public void save(Book book) {

		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		String bookName = book.getBookName();
		String bookWriter = book.getBookWriter();
		String bookPublisher = book.getBookPublisher();
		BigDecimal bookPrice = book.getBookPrice();
		Integer bookCategory = book.getBookCategory().getCategoryId();
		String insertDate =  book.getInsertDate();
//		String insertDate = "0000-00-00 00:00:00";
//		Date insertDate = book.getInsertDate();

		try {

			String query = "INSERT INTO `books` "
					+ "(`bId`, `bName`, `bWriter`, `bPublisher`, `bPrice`, `categoryId`, `bDate`) "
					+ "VALUES (NULL, ?, ?, ?, ?, ?, ?)";

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, bookName);
			preparedStmt.setString(2, bookWriter);
			preparedStmt.setString(3, bookPublisher);
			preparedStmt.setString(4, bookPrice.toString());
			preparedStmt.setString(5, bookCategory.toString());
			preparedStmt.setString(6, insertDate);
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
				BigDecimal bPrice = rs.getBigDecimal("bPrice");
				Category bCategory = new Category(Integer.valueOf(rs.getString("bCategory")));
				String insertDate = rs.getString("bDate");
//				Date insertDate = getInsertDate(bId);
				Book book = new Book(bId, bName, bWriter, bPublisher, bPrice, bCategory, insertDate);
				list.add(book);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("An error occured while retrieving " + "all books: " + e.toString());
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
		Integer bookCategory = book.getBookCategory().getCategoryId();

		try {

			String query = " UPDATE `books` SET `bName` = ?, `bWriter` = ?, `bPublisher` = ?, `bCategory` = ? WHERE `books`.`bId` = ?";
			preparedStmt = connection.prepareStatement(query);

			preparedStmt.setString(1, bookName);
			preparedStmt.setString(2, bookWriter);
			preparedStmt.setString(3, bookPublisher);
			preparedStmt.setLong(4, bookCategory);
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
		BigDecimal bookPrice;
		Category bookCategory;
		String insertDate;
//		Date insertDate;
		Book book = null;
		try {

			String query = "SELECT * FROM `books` WHERE `bId` = ?";

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, bId);

			ResultSet rs = null;
			preparedStmt.execute();
			rs = preparedStmt.getResultSet();

//			while (rs.next()) {
			rs.next();
			bookName = rs.getString("bName");
			bookWriter = rs.getString("bWriter");
			bookPublisher = rs.getString("bPublisher");
			bookPrice = rs.getBigDecimal("bPrice");
			bookCategory = new Category(Integer.valueOf(rs.getString("bCategory")));
			insertDate = rs.getString("bDate");
//			insertDate = getInsertDate(bookId);
			book = new Book(bookId, bookName, bookWriter, bookPublisher, bookPrice, bookCategory, insertDate);

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

	@Override
	public Date getInsertDate(Integer bookId) {
		Connection connection = DaoConnection.connect();
		PreparedStatement preparedStmt = null;
		String bId = bookId.toString();
		Date insertTime = null;
		try {

			String query = "SELECT `bDate` FROM `books` WHERE `bId` = ?";

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, bId);

			ResultSet rs = null;
			preparedStmt.execute();
			rs = preparedStmt.getResultSet();

			rs.next();
			insertTime = rs.getDate("bDate");

			System.out.println("Book is Successfully Found.");

		} catch (SQLException e) {
			System.out.println("Date select catch");
			e.printStackTrace();
		} finally {
			DaoConnection.closeAll(preparedStmt, connection);
		}

		return insertTime;
	}

}
