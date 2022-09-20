package dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;


/**
 * Mysql database name: sql5520691
 * Mysql table name: t_book
 * 
 * @author 
 *
 */
public class BookDao {
	
	/**
	 * Add a book
	 * @param con
	 * @param book
	 * @return 1 or 0 (1: add success; 2: add fail)
	 * @throws Exception
	 * r 
	 */
	public static int add(Connection con,Book book)throws Exception{		
		//add more codes here
		return  1;
				
	}

	
	
	/**
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 * 
	 * @author 
	 */
	public static int delete(Connection con,Book book)throws Exception{
		//add more codes here
		return 1;	
	}
	
	
	
	/**
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 * 
	 * @author 
	 */
	public static int update(Connection con,Book book)throws Exception{
		//add more codes here
		return 1;
	}
	
	
}//
