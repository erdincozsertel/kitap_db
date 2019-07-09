package controller;

import java.io.IOException;
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
 * Servlet implementation class IndexController
 * 
 * @author erdincozsertel
 */
@WebServlet("")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao bookDao = new BookDaoImpl();
		List<Book> bookList = bookDao.getBookList();
		request.setAttribute("bookList", bookList);
		RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/index.jsp");
		req.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = null;
		if (request.getParameter("SignUp") != null) {
			action = "SignUp";
		} else if (request.getParameter("LogIn") != null) {
			action = "LogIn";
		} else if (request.getParameter("BookRegister") != null) {
			action = "BookRegister";
		}

		try {
			if (action == "SignUp") {
				signUp(request, response);
			} else if (action == "LogIn") {
				logIn(request, response);
			} else if (action == "BookRegister") {
				bookRegister(request, response);
			} else {
				RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/index.jsp");
				req.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/register.html");
		req.forward(request, response);
	}

	private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/login.html");
		req.forward(request, response);
	}

	private void bookRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDao categoryDao = new CategoryDaoImpl();
		List<Category> categoryList = categoryDao.getCategoryList();
		request.setAttribute("categoryList", categoryList);
		RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/bookRegister.jsp");
		req.forward(request, response);
	}
}
