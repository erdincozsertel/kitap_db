package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;

public class BookController {
	
	public boolean controler() {
		return false;		
	}
	
	public static boolean control(String bookName, String bookWriter, String bookPublisher, String bookCategory) 
	//Book Controller
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db", "root", "");
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM `books` WHERE `bName` = `"+bookName+"` AND `bWriter` = `"+bookWriter+"` AND `bPublisher` = `"+bookPublisher+"` AND `category` = `"+bookCategory+"`";
			ResultSet rs = st.executeQuery(sql);
			return rs.next();
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
				
	}

}
