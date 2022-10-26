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
 * Online Database Information:
 * Mysql database name: ZHB1hpTWfE
 * Host: sql5.freesqldatabase.com
 * Database name: sql5520691
 * Database user: sql5520691
 * Database password: XBmgbLjYuM
 * 
 * Tables: (1)t_book
 *         (2)t_user
 * 
 * @author Caihong
 *
 */
public class UserDao {
	
	/**
	 * Login Verification 
	 * @param con
	 * @return user
	 * @throws Exception
	 */
	public static User login(Connection con,User user)throws Exception{
		
		User resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
				
		PreparedStatement pstmt=con.prepareStatement(sql);  /**<  It returns an instance of ResultSet when a select query is executed.*/
		
		/**< Change set result accordingly by passing a created user and u */	
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		
		/**< A ResultSet is a Java object that contains the results of executing an SQL query. 
	         In other words, it contains the rows that satisfy the conditions of the query.  */
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			resultUser = new User();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));		
		}
		
		/**< Return a User instance. If the userName and password dont match, it will return null */
		return  resultUser;
		
		
	}

}//
