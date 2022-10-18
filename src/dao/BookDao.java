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
 * bookID (dataTpye: int; primary key)
 * bookName (dataTpye: String)
 * isAvailable (dataTpye: int; 1 is available, 0 is not available)
 * lendTo (dataTpye: String; the person's name)
 * 
 * @author Caihong
 *
 */
public class BookDao {
	
	/**
	 * Add a book
	 * @param con
	 * @param book
	 * @return 1:  success 0:  fail  
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
	 * @return 1:  success 0: fail        
	 * @throws Exception
	 * 
	 * @author 
	 */
	public static int delete(Connection con,Books book)throws Exception{
		//add more codes here
		return 1;	
	}
	
	
	
	/**
	 * Change a book's available status with given bookName
	 * @param con
	 * @param book
	 * @return 1:  success 0: fail         
	 * @throws Exception
	 * 
	 * @author 
	 */
	//URL:alvinalexander.com/java/java-mysql-update-query-example/
	public static int update_isAvailable(Connection con,int availableStatus,String bookName)throws Exception{
		String sql="update t_book set isAvailable = ? where bookName = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, availableStatus);
		pstmt.setString(2, bookName);
		return  pstmt.executeUpdate();
		
	}
	
	
	/**
	 * Return total numbers of books in the t_book table in the database
	 * @param con
	 * @return
	 * @throws Exception
	 */
	//URL:tutorialspoint.com/how-to-count-rows-count-and-java#:~:text=The%20SQL%20Count()%20function,of%20rows%20in%20a%20table.
	public static int getTotalNum(Connection con)throws Exception{
		int totalNum = 0;
		String sql="select count(*) from t_book";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery(sql);
		
		rs.next();
		totalNum = rs.getInt(1);
		
		return totalNum;	
	}
	
	
     /**
	 * 
	 * @param con
	 * @param id
	 * @return a book instance with given book ID
	 * @throws Exception 
	 */
	public static  Books returnBook(Connection con,int id)throws Exception{
		//add more codes here
		Books resultBook=null;
		String sql="select * from t_book where bookID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		
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
	
	
	public static void main(String[] args) throws Exception {
	
	DataBaseUtility dbUtil = new DataBaseUtility(); 
	Connection con = dbUtil.getCon(); 	
        BookDao.returnBook(con, 1).getBookName();
        
        //Test: returnBook   Pass!
        //System.out.println(BookDao.returnBook(con, 1).getBookName());
	     
	//Test: getTotalNum  Pass!
	//System.out.println(BookDao.getTotalNum(con));
	    
	//Test: update_isAvailable Pass!
	//System.out.println("update_isAvailable success ? " + BookDao.update_isAvailable( con,0,"Harry Potter 1")  );
	

  }
	

     


}//
