package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User(username, password);

		boolean loginStatus;
		UserDao userDao = new UserDaoImpl();
		loginStatus = userDao.isUser(user);

		if (loginStatus)
			pw.println("Login Success...!");
		// Sesion start
		else
			pw.println("Login Failed...!");
		pw.close();
	}

}
