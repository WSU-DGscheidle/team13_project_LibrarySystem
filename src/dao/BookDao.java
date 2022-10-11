package dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Books;
import model.User;
import utility_public.DataBaseUtility;


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
	 * @return 1:  success
	 *         0:  fail
	 * @throws Exception
	 * r 
	 */
	public static int add(Connection con,Books book)throws Exception{		
		//add more codes here
		return  1;
				
	}

	
	
	/**
	 * 
	 * @param con
	 * @param book
	 * @return 1:  success
	 *         0:  fail
	 * @throws Exception
	 * 
	 * @author 
	 */
	public static int delete(Connection con,Books book)throws Exception{
		//add more codes here
		return 1;	
	}
	
	
	
	/**
	 * 
	 * @param con
	 * @param book
         * @return 1:  success
	 *         0:  fail
	 * @throws Exception
	 * 
	 * @author 
	 */
	public static int update(Connection con,Books book)throws Exception{
		//add more codes here
		return 1;
	}
	
	
        /**
	 * 
	 * @param con
	 * @param book
	 * @return a book instance
	 * @throws Exception
	 */
	public static  Books returnBook(Connection con,Books book)throws Exception{
		//add more codes here
		Books resultBook=null;
		String sql="select * from t_book where bookID=? bookName=? isAvailable=? lendTo=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, book.getBookID());
		pstmt.setString(2, book.getBookName());
		pstmt.setInt(3, book.isAvailable());
		pstmt.setString(4, book.getLendTo());
		
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next()) {
			resultBook = new Books();
			resultBook.setBookID(rs.getInt("bookID"));
			resultBook.setBookName(rs.getString("bookName"));
			resultBook.setAvailable(rs.getInt("isAvailable"));
			resultBook.setLendTo(rs.getString("lendTo"));
		}
		
		
		return resultBook;
	}
	
	
	//Temp test
	public static void main(String[] args) {
		Books abook = new Books();
		DataBaseUtility dbUtil = new DataBaseUtility(); 
		Connection con = null; 
		try {
			con =dbUtil.getCon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Books currentBook = BookDao.returnBook(con, abook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}



}//
