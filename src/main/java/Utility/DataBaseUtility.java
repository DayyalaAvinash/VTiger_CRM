package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

/**
 * @author AVINASH D 
 * This Class Is Used To Connect With DataBase
 * And Fetch Data From DataBase
 * And Update Data To DataBase
 * And Disconnect With DataBase
 */
public class DataBaseUtility {

	Connection con;
	
	/**
	 * This Method Is Used To Connect With DataBase By Passing Url, Username and Password 
	 * @param url
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void getconnectwith_DB(String url,String username,String password) throws SQLException {
		
		//Create Driver Instance
		Driver driver= new Driver();
		
		//Register With Driver
		DriverManager.registerDriver(driver);
		
		//Connect With Data Base
		 con = DriverManager.getConnection(url,username,password);
		
	}
	
	/**
	 * This Method Is Used To Connect With DataBase With Valid Credentials
	 * @throws SQLException
	 */
	public void getconnectwith_DB() throws SQLException{
		
		//Create Driver Instance
		Driver driver=new Driver();
		
		//Register With Driver
		DriverManager.registerDriver(driver);
		
		//Connect With Data base
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium", "root", "root");
		
	}
	
	/**
	 * This Method Is Used Fetch Data From DataBase
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet fetchdatafrom_DB(String query) throws SQLException {
		
		//Create Statement
		Statement stat = con.createStatement();
		
		//Execute The Query
		ResultSet data = stat.executeQuery(query);
		
		return data;
		
	}
	
	/**
	 * This Method Is Used To Disconnect With DataBase
	 * @throws SQLException
	 */
	public void disconnectwith_DB() throws SQLException {
		
		//Disconnect With Data Base
		con.close();
		
	}
	
	/**
	 * This Method Is Used To Update Data To DataBase
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int updateDataToDB(String query) throws SQLException {
		
		//Create Statement
		Statement stat = con.createStatement();
		
		//Update The Query (Or) Write Back Data Into Data Base
		int res = stat.executeUpdate(query);
		// -1 (Indicates) --> Data Inserted But With The Warning
	    //  0 (Indicates) --> Query Not Executed
	    //  1 (Indicates) --> Successfully data got inserted without any warning
		
		return res;
		
	}
	
	
}
