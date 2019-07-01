package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.Book;

public class BookDao implements Dao<Book> {
	// This will be database instead of list
	private List<Book> books = new ArrayList<>();

//    public BookDao() {
//        books.add(new Book("book Name", "book Writer", "book Publisher", "book Category"));
//                
//    }

	@Override
	public Optional<Book> get(long id) {
		// Anlamadim
		return Optional.ofNullable(books.get((int) id));
	}

	@Override
	public List<Book> getAll() {
		// return all books in db
		return books;
	}

	public boolean exist(Book book) {
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
			String query = " \"SELECT * FROM `books` (bName, bWriter, bPublisher, category)"
					+ " values (?, ?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
//			preparedStmt.setString(1, bookId);
			preparedStmt.setString(1, bookName);
			preparedStmt.setString(2, bookWriter);
			preparedStmt.setString(3, bookPublisher);
			preparedStmt.setString(4, bookCategory);

			// execute the preparedstatement
			if (preparedStmt.execute()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public void save(Book book) {
//		String bookId = book.getBookId().toString();
		String bookName = book.getBookName();
		String bookWriter = book.getBookWriter();
		String bookPublisher = book.getBookPublisher();
		String bookCategory = book.getBookCategory();
		if (exist(book)) {
			System.out.println("<meta http-equiv='refresh' content='3;URL=bookRegister.html'>");// redirects after 3
																								// seconds
			System.out.println("<p style='color:red;'>" + "Book " + bookName + " exist...!" + "</p>");
//			return false;
		} else {
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

				if (preparedStmt.execute()) {
					System.out.println("Data is Successfully Inserted into users Table");
				} else {
					System.out.println("Error!");
				}

//				return true;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("<meta http-equiv='refresh' content='3;URL=bookRegister.html'>");// redirects after 3
																									// seconds
				System.out.println("<p style='color:red;'>" + "Insert Error...!" + "</p>");
				e.printStackTrace();
//			return false;
			}
		}
	}

	@Override
	public void update(Book book, String[] params) {
		book.setBookName(Objects.requireNonNull(params[0], "BookName cannot be null"));
		book.setBookWriter(Objects.requireNonNull(params[1], "Book Writer cannot be null"));
		book.setBookPublisher(Objects.requireNonNull(params[2], "Book Publisher cannot be null"));
		book.setBookCategory(Objects.requireNonNull(params[3], "Book Category cannot be null"));
		books.remove(book);
		books.add(book);
	}

	@Override
	public void delete(Book book) {
		// This function will delete user from db
		books.remove(book);
	}

	public void addBook(Book book) {
		save(book);
	}

}
