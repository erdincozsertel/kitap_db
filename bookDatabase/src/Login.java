

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		boolean loginStatus = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db","root","");
	        Statement st = conn.createStatement();
            String sql = "SELECT * FROM `users` WHERE `username` = '"+username+"'";
            ResultSet rs= st.executeQuery(sql);
            if(rs.next()==true)
            {
            	pw.println("User "+username+ " exist...!");
                sql = "SELECT * FROM `users` WHERE `username` = '"+username+"' AND `password` = '"+password+"'";
                rs= st.executeQuery(sql);
                if(rs.next()==true) 
                {
                	pw.println("Password is true...!");
                	loginStatus=true;
                }
                else
                {
                	pw.println("Wrong Password...!");
                }
            }
            else
            {
            	pw.println("User "+username+ " does not exist...!");
            }

			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(loginStatus)
		pw.println("Login Success...!");
		else
		pw.println("Login Failed...!");
		pw.close();
		}

}
