package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookDaoImpl;
import dao.CategoryDao;
import dao.CategoryDaoImpl;
import model.Book;
import model.Category;

/**
 * Servlet implementation class BookController
 * 
 * @author erdincozsertel
 */
@WebServlet("/bookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao bookDao = new BookDaoImpl();
		List<Book> bookList = bookDao.getBookList();
		request.setAttribute("bookList", bookList);
		RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/ListBook.jsp");
		req.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = null;
		if (request.getParameter("deleteButton") != null) {
			action = "Delete";
		} else if (request.getParameter("editButton") != null) {
			action = "Edit";
		} else if (request.getParameter("editPage") != null) {
			action = "EditPage";
		}

		try {
			if (action == "Delete") {
				deleteBook(request, response);
			} else if (action == "Edit") {
				editBookPage(request, response);
			} else if (action == "EditPage") {
				editBook(request, response);
			} else {
				insertBook(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		String bookName = request.getParameter("bookName");
		String writerName = request.getParameter("writerName");
		String publisherName = request.getParameter("publisherName");
		BigDecimal bookPrice = new BigDecimal(request.getParameter("bookPrice"));
		Category bookCategory = new Category(Integer.valueOf(request.getParameter("bCategory")));

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = localDate.format(formatter);

//		Date insertDate = Date.valueOf(formattedDateTime);
//		System.out.println(insertDate);

		Book book = new Book(bookName, writerName, publisherName, bookPrice, bookCategory, formattedDateTime);
		if (bookName.isEmpty() || writerName.isEmpty() || publisherName.isEmpty()
				|| bookPrice.compareTo(BigDecimal.ZERO) < 0) {
			request.getRequestDispatcher("/WEB-INF/bookRegister.html").forward(request, response);
		} else {
			BookDao bookDao = new BookDaoImpl();
			bookDao.save(book);
			doGet(request, response);
		}
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("deleteButton");
		Integer bookId = Integer.valueOf(id);
		if (bookId != null) {
			BookDao bookDao = new BookDaoImpl();
			bookDao.delete(bookId);
		}
		doGet(request, response);
	}

	private void editBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		BookDao bookDao = new BookDaoImpl();

		String id = request.getParameter("bookId");
		Integer bookId = Integer.valueOf(id);
		String bookName = request.getParameter("bookName");
		String writerName = request.getParameter("writerName");
		String publisherName = request.getParameter("publisherName");
		BigDecimal bookPrice = new BigDecimal(request.getParameter("bookPrice"));
		Category bookCategory = new Category(Integer.valueOf(request.getParameter("bCategory")));
		String insertDate = request.getParameter("insertDate");
//		Date insertDate = bookDao.getInsertDate(bookId);
		Book book = new Book(bookId, bookName, writerName, publisherName, bookPrice, bookCategory, insertDate);
		book.setInsertDate(book.getInsertDate().replace('T', ' '));
		if (bookId != null || bookName.isEmpty() || writerName.isEmpty() || publisherName.isEmpty()
				|| bookPrice.compareTo(BigDecimal.ZERO) < 0 || bookCategory.getCategoryName().isEmpty()
				|| insertDate.isEmpty()) {
			request.setAttribute("book", book);
			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/bookEdit.jsp");
			req.forward(request, response);
		} else {
			bookDao.update(book);
			doGet(request, response);
		}
	}

	private void editBookPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		BookDao bookDao = new BookDaoImpl();
		CategoryDao categoryDao = new CategoryDaoImpl();
		Integer bookId = Integer.valueOf(request.getParameter("editButton"));
		Book book = null;
		if (bookId != null) {
			book = bookDao.getBook(bookId);
		}
		if (book != null) {
			List<Book> bookList = new ArrayList<Book>();
			bookList.add(book);
			request.setAttribute("bookList", bookList);
			List<Category> categoryList = categoryDao.getCategoryList();
			request.setAttribute("categoryList", categoryList);
			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/bookEdit.jsp");
			req.forward(request, response);
		} else {
			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/index.jsp");
			req.forward(request, response);
		}
	}

}
