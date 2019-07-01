package controller;

import java.io.IOException;

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
		String bookN = request.getParameter("bookName");
		String writerN = request.getParameter("writerName");
		String publisherN = request.getParameter("publisherName");
		String category = request.getParameter("categoryName");
		Book book = new Book(bookN, writerN, publisherN, category);

		if (bookN.isEmpty() || writerN.isEmpty() || publisherN.isEmpty() || category.isEmpty()) {
			RequestDispatcher req = request.getRequestDispatcher("bookRegister.html");
			req.include(request, response);
		} else {
			BookDao bookDao = new BookDaoImpl();
			bookDao.save(book);
			RequestDispatcher req = request.getRequestDispatcher("reg_Success.html");
			req.forward(request, response);

		}
	}

}
