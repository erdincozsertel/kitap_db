package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;

public class RegisterController {
	
	public boolean controler() {
		return false;		
	}
	
	public static boolean control(String username, String Password, int isAdmin) 
	//Register Controller
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db","root","");
	        Statement st = conn.createStatement();
            String sql = "SELECT * FROM `users` WHERE `username` = '"+username+"'";
            ResultSet rs= st.executeQuery(sql);
            if(rs.next()==true)
            {
            	System.out.println("User "+username+" exist...!");
            	return false;
            }
            else
            {
            	return true;
            }  
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			
	}

}
