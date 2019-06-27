package controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
	
	public boolean controler() {
		return false;		
	}
	public static boolean control(String username, String password)
	//Login Controller
	{	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_book_db","root","");
	        Statement st = conn.createStatement();
            String sql = "SELECT * FROM `users` WHERE `username` = '"+username+"'";
            ResultSet rs= st.executeQuery(sql);
            if(rs.next()==true)
            {
            	System.out.println("User "+username+ " exist...!");
                sql = "SELECT * FROM `users` WHERE `username` = '"+username+"' AND `password` = '"+password+"'";
                rs= st.executeQuery(sql);
                if(rs.next()==true) 
                {
                	System.out.println("Password is true...!");
                	return true;
                }
                else
                {
                	System.out.println("Wrong Password...!");
                	return false;
                }
            }
            else
            {
            	System.out.println("User "+username+ " does not exist...!");
            	return false;
            }

			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;		
	}
	
	public boolean control(String username, String Password, int isAdmin) 
	//Register Controller
	{

		return false;		
	}
	
	public boolean control(String bookName, String bookWriter, String bookPublisher, String bookCategory) 
	//Book Controller
	{

		return false;		
	}

}
