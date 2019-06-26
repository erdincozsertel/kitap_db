

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
		String idusers = "NULL";
		
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
			try {
				Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db","root","");
	            Statement st = conn.createStatement();
	            
	            String sql = "SELECT * FROM `users` WHERE `username` = '"+username+"'";
	            ResultSet rs= st.executeQuery(sql);
	            if(rs.next())
	            {
	            	pw.println("User "+username+" exist...!");
	            }
	            else
	            {
	            	sql = "INSERT INTO `users` (`idusers`, `username`, `uPass`, `isAdmin`) VALUES ('"+idusers+"','"+username+"','"+password+"','"+0+"')";
		            st.executeUpdate(sql);
		            pw.println("Data is Successfully Inserted into users Table");
		            insertSuccess=true;
	            }
	            
	            
	            


			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				insertSuccess=false;
				RequestDispatcher req = request.getRequestDispatcher("register.html");
				req.include(request, response);
				e.printStackTrace();
			}
			if(insertSuccess)
			{
				RequestDispatcher req = request.getRequestDispatcher("reg_Success.html");
				req.forward(request, response);
			}
			else
			{
				pw.println("<meta http-equiv='refresh' content='3;URL=index.html'>");//redirects after 3 seconds
            	pw.println("User "+username+" exist...!");
				pw.println("<p style='color:red;'>User or password incorrect!</p>");
			}
		}
	}

}
