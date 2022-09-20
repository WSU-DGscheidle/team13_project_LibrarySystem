package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;


/**
 * This Class is a "Data Access Object" shorts for DAO, it only have one
 * method "login" which takes in an instance of connection and an instance of user to connect with MySql database
 * and return a User instance.
 * 
 * Mysql database name: sql5520691
 * Mysql table name: t_user
 * There are currently 3 users in this table:
 * username: Jim  password:123456  id:1
 * username: Lucy  password:123456  id:2
 * username: Admin  password:654321  id:3
 * 
 * @author Novak
 *
 */
public class UserDao {
	
	/**
	 * Login Verification 
	 * @param con
	 * @param 
	 * @return user
	 * @throws Exception
	 */
	public static User login(Connection con,User user)throws Exception{
		
		User resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
		
		//Performing Database Operations in Java | SQL CREATE, INSERT, UPDATE, DELETE and SELECT
		//Tutorial: geeksforgeeks.org/how-to-use-preparedstatement-in-java/
		PreparedStatement pstmt=con.prepareStatement(sql);  //It returns an instance of ResultSet when a select query is executed.
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		
	     //A ResultSet is a Java object that contains the results of executing an SQL query. 
	    //In other words, it contains the rows that satisfy the conditions of the query.  
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			resultUser = new User();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));		
		}
		
		return  resultUser;
		
		
	}

}//
