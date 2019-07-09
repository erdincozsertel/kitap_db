package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import model.User.Gender;

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
		Gender gender = Gender.valueOf(request.getParameter("gender"));
//		java.util.Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthDate"));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localBirthDate = LocalDate.parse(request.getParameter("birthDate"));
		Date birthDate = Date.valueOf(localBirthDate);


		

		if (username.isEmpty() || password.isEmpty() || birthDate==null) {
			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/register.html");
			req.include(request, response);
		} else {
			boolean insertSuccess = false;
			User user = new User(username, password, gender, birthDate);

			UserDao userDao = new UserDaoImpl();
			insertSuccess = userDao.save(user);

			if (insertSuccess) {
//				RequestDispatcher req = request.getRequestDispatcher("/");
//				req.forward(request, response);
				response.sendRedirect("/bookDatabase");
			} else {
				pw.println("<meta http-equiv='refresh' content='3;URL=register.html'>");// redirects after 3 seconds
				pw.println("<p style='color:red;'>" + "User " + username + " exist...!" + "</p>");
			}
		}
	}
}
