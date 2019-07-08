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
 */
@WebServlet("/loginController")
public class LoginController extends HttpServlet {
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
		
		User user = new User(username, password);

		boolean loginStatus;
		UserDao userDao = new UserDaoImpl();
		loginStatus = userDao.isUser(user);

		if (loginStatus) {
			pw.println("Login Success...!");
			// TODO Session start
//			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/index.jsp");
//			req.forward(request, response);
			response.sendRedirect("/bookDatabase");
		} else {
			pw.println("Login Failed...!");
			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/login.html");
			req.forward(request, response);
		}
		pw.close();
	}

}
