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
 * Servlet implementation class Register
 */
@WebServlet("/registerController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.isEmpty() || password.isEmpty()) {
			RequestDispatcher req = request.getRequestDispatcher("register.html");
			req.include(request, response);
		} else {
			boolean insertSuccess = false;

			User user = new User(username, password);

			UserDao userDao = new UserDaoImpl();
			insertSuccess = userDao.save(user);

			if (insertSuccess) {
				RequestDispatcher req = request.getRequestDispatcher("index.jsp");
				req.forward(request, response);
			} else {
				pw.println("<meta http-equiv='refresh' content='3;URL=register.html'>");// redirects after 3 seconds
				pw.println("<p style='color:red;'>" + "User " + username + " exist...!" + "</p>");
			}
		}
	}
}
