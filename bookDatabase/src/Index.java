
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Book List</title></head>");
		out.println("<body>");
		out.println("<center><h1>Book List</h1>");
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db", "root", "");
			String query = " SELECT * FROM `books` ";
			preparedStmt = conn.prepareStatement(query);
//		    String query = "SELECT Employees.SSN, Employees.Name, " + "Employees.Salary, "
//		        + "Employees.Hiredate, Location.Location " + "FROM Location " + "INNER JOIN Employees "
//		        + "ON Location.Loc_Id = Employees.Loc_Id " + "ORDER BY " + orderBy + " " + orderByDir
//		        + ";";
			ResultSet rs = preparedStmt.executeQuery(query);
			while (rs.next()) {
				Integer bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bWriter = rs.getString("bWriter");
				String bPublisher = rs.getString("bPublisher");
				String category = rs.getString("category");
				out.println(bId + "::");
				out.println(bName + "::");
				out.println(bWriter + "::");
				out.println(bPublisher + "::");
				out.println(category + "::");
//				out.println();
			}
		} catch (SQLException e) {
			out.println("An error occured while retrieving " + "all employees: " + e.toString());
		} catch (ClassNotFoundException e) {
			throw (new ServletException(e.toString()));
		} finally {
			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
			}
		}
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
