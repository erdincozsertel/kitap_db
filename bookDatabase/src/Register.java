

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RegisterController;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
//		String first_name = request.getParameter("first_name");
//		String last_name = request.getParameter("last_name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		String idusers = "NULL";
		int isAdmin;
		if (request.getParameter("isAdmin") != null) {
			isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
		}
		else {
			isAdmin=0;
		}		
//		System.out.println(MD5edPass);
//		String address = request.getParameter("address");
//		String contact = request.getParameter("contact");
		
		if(username.isEmpty() || password.isEmpty())
		{
			RequestDispatcher req = request.getRequestDispatcher("register.html");
			req.include(request, response);
		}
		else
		{
			boolean insertSuccess=false;
			//Move to User Dao Insert begins here
			try {
	            	if (RegisterController.control(username, password, isAdmin)) {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db",
								"root", "");
						Statement st = conn.createStatement();
						String sql = "INSERT INTO `users` (`idusers`, `username`, `password`, `isAdmin`) VALUES (NULL, '"
								+ username + "', '" + password + "', '" + isAdmin + "')";
						ResultSet rs = st.executeQuery(sql);
						pw.println("Data is Successfully Inserted into users Table");
						insertSuccess = true;
					}         
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				insertSuccess=false;
				RequestDispatcher req = request.getRequestDispatcher("register.html");
				req.include(request, response);
				e.printStackTrace();
			}
			//Move to User Dao Insert ends here
			if(insertSuccess)
			{
				RequestDispatcher req = request.getRequestDispatcher("reg_Success.html");
				req.forward(request, response);
			}
			else
			{
				pw.println("<meta http-equiv='refresh' content='3;URL=register.html'>");//redirects after 3 seconds
				pw.println("<p style='color:red;'>"+"User "+username+" exist...!" +"</p>");
			}
		}
	}

}
