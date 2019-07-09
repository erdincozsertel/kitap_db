package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

/**
 * Servlet implementation class Login
 * 
 * @author erdincozsertel
 */
@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		User user;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//TODO: password hashing

		if (username.isEmpty() || password.isEmpty()) {
			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/login.html");
			req.forward(request, response);
		} else {
			user = new User(username, password);
			boolean loginStatus;
			UserDao userDao = new UserDaoImpl();
			loginStatus = userDao.isUser(user);

			if (loginStatus) {
				// TODO Session start
				response.sendRedirect("/bookDatabase");
			} else {
				RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/login.html");
				req.forward(request, response);
			}
		}
	}

}
