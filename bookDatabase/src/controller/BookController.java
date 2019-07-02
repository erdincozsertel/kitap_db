package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookDaoImpl;
import model.Book;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/bookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String bookName = request.getParameter("bookName");
		String writerName = request.getParameter("writerName");
		String publisherName = request.getParameter("publisherName");
		String bookCategory = request.getParameter("categoryName");
		Book book = new Book(bookName, writerName, publisherName, bookCategory);

		if (bookName.isEmpty() || writerName.isEmpty() || publisherName.isEmpty() || bookCategory.isEmpty()) {
			RequestDispatcher req = request.getRequestDispatcher("bookRegister.html");
			req.include(request, response);
		} else {
			BookDao bookDao = new BookDaoImpl();
			bookDao.save(book);
			RequestDispatcher req = request.getRequestDispatcher("ListBook.jsp");
			req.forward(request, response);

		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao bookDao = new BookDaoImpl();
		List<Book> bookList = bookDao.getBookList();
		request.setAttribute("bookList", bookList);
		RequestDispatcher req = request.getRequestDispatcher("ListBook.jsp");
		req.forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter ("deleteButton");
		Integer bookId = Integer.valueOf(id);


		System.out.println("bookController");
		PrintWriter pw = response.getWriter();
		pw.println(id);
		//
		BookDao bookDao = new BookDaoImpl();
		bookDao.delete(bookId);
		super.doDelete(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter ("deleteButton");
		Integer bookId = Integer.valueOf(id);


		System.out.println("bookController");
		PrintWriter pw = response.getWriter();
		pw.println(id);
		//
		BookDao bookDao = new BookDaoImpl();
		bookDao.delete(bookId);
		super.doPut(request, response);
	}

}
