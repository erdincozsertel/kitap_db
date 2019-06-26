

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet("/bookRegister")
public class BookRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String bookN = request.getParameter("bookName");
		String writerN = request.getParameter("writerName");
		String publisherN = request.getParameter("publisherName");
		String category = request.getParameter("categoryName");
		
		if(bookN.isEmpty() || writerN.isEmpty() || publisherN.isEmpty() || category.isEmpty())
		{
			RequestDispatcher req = request.getRequestDispatcher("BookRegister.html");
			req.include(request, response);
		}
		else
		{
			boolean insertSuccess=false;
			try {
				Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db","root","");
	            Statement st = conn.createStatement();
	            
	            String sql = "INSERT INTO `books` (`bId`, `bName`, `bWriter`, `bPublisher`, `category`) VALUES (NULL, '"+bookN+"', '"+writerN+"', '"+publisherN+"', '"+category+"');";
	            st.executeUpdate(sql);
	            pw.println("Data is Successfully Inserted into users Table");
	            insertSuccess=true;
	            


			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				insertSuccess=false;
				RequestDispatcher req = request.getRequestDispatcher("BookRegister.html");
				req.include(request, response);
				e.printStackTrace();
			}
			if(insertSuccess)
			{
				RequestDispatcher req = request.getRequestDispatcher("reg_Success.html");
				req.forward(request, response);
			}			
		}
	}

}
