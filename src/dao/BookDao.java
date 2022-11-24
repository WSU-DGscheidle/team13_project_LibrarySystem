package dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Books;
import model.User;
import utility_public.DataBaseUtility;
import java.util.ArrayList;

/** 
 * The purpose of this class is to assist UserView and AdminView
 * in communicating with the online MySQL database and manipulating data within the database.
 * @author Caihong
 * 
 */
public class BookDao {
	
	
	/**
	 * Add a book to the database
	 * @param con
	 * @param book
	 * @return 1:  success 
	 *         0:  fail  
	 * @throws Exception
	 * 
	 */
	public static int add(Connection con,Books book)throws Exception{
		
		String sql="insert into t_book values(null,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setInt(2, book.getAvailable());
		pstmt.setString(3, book.getLendTo());
	
		return pstmt.executeUpdate();
				
	}

	
	/**
	 * delete a book from the database by passing bookId as parameter
	 * @param con
	 * @param id
	 * @return 1:  success 
	 *         0:  fail  
	 * @throws Exception
	 * 
	 */
	public static int delete_byID(Connection con,int id)throws Exception{
		String sql="delete from t_book where bookID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
		
	}
	
	/**
	 * Change a book's available status('1' indicate available, '0' indicate not available) with given bookName
	 * @param con
	 * @param int availableStatus
	 * @param bookName
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
	 * Given a book's ID update the borrowTime info in the database
	 * @param con
	 * @param time
	 * @param id
	 * @return 1:  success 0: fail  
	 * @throws Exception
	 */
	public static int update_borrowTime(Connection con,String time,int id)throws Exception{
		String sql="update t_book set borrowTime = ? where bookID = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, time);
		pstmt.setInt(2, id);
		return  pstmt.executeUpdate();
		
	}
	
	
	/**
	 * Return total numbers of books in the t_book table in the database
	 * @param con
	 * @return totalNum
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
	 * Return a book instance by given book id
	 * @param con
	 * @param id
	 * @return book
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
			resultBook.setBorrowTime(rs.getString("borrowTime"));
			resultBook.setLendTo(rs.getString("lendTo"));
		}
		
		
		return resultBook;
	}
	
	/**
	 * Return a list of all books in the database
	 * @throws Exception
	 */
	public static ArrayList<Books> getBooksList(Connection con) throws Exception    {
	    ArrayList<Books> booksList = new ArrayList<Books>();
	     
	    int totalNum  = BookDao.getTotalNum(con);
	    
	    //the bookID start with 1
	    for(int i=1; i<totalNum + 1  ; i++) {  
	    	booksList.add(  BookDao.returnBook(con, i)   );    	
	    }
	    return booksList;

	   
	}
	
	
	/**
	 * Given bookID,return an array of strings that show the information of one book
	 * @param con 
	 * @param int bookID
	 * @throws Exception
	 */
	public static Object[] getArrayOfOneBook(Connection con,int bookID) throws Exception    {
		    
	    Books aBook =  BookDao.returnBook(con, bookID); 
	    
        Object[] arr;         
        arr = new Object[5]; //5 columns is fixed: bookID,bookName,isAvailable,lendTo,borrowTime
	    
        arr[0]=aBook.getBookID(); 
        arr[1]=aBook.getBookName();
        arr[2]=aBook.getAvailable();
        arr[3]=aBook.getLendTo();
        arr[4]=aBook.getBorrowTime();
	    
	    return arr;

	   
	}
	
	/**
	 * Given a book name,return total quantity of the books in the t_book table from the database
	 * @param con
	 * @return
	 * @throws Exception
	 */
	//URL:tutorialspoint.com/how-to-count-rows-count-and-java#:~:text=The%20SQL%20Count()%20function,of%20rows%20in%20a%20table.
	public static int getBookQuantity(Connection con,String name)throws Exception{
		int quantity= 0;
		ArrayList<Books> bookList = null;
		bookList = BookDao.getBooksList(con);
	
		for(Books book : bookList) {
			if(book.getBookName().equalsIgnoreCase(name)) {
				quantity=quantity + 1;
			}
		}		
		return quantity;	
	}
	
	public static void main(String[] args) throws Exception {
	
	DataBaseUtility dbUtil = new DataBaseUtility(); 
	Connection con = dbUtil.getCon(); 	
    //BookDao.returnBook(con, 1).getBookName();
        
    //Test: returnBook   Pass!
    //System.out.println(BookDao.returnBook(con, 1).getBookName());
	     
	//Test: getTotalNum  Pass!
	//System.out.println(BookDao.getTotalNum(con));
	    
	//Test: update_isAvailable Pass!
	//System.out.println("update_isAvailable success ? " + BookDao.update_isAvailable( con,0,"Harry Potter 1")  );
	
    //Test: add(Connection con,Books book)  Pass!
	//Books newBook = new Books("Harry Potter 1",21,1,null);  lendTO default value is : NULL in the database
	//Books newBook = new Books("TestBook1", 1,"Jim");
	//System.out.print( BookDao.add(con, newBook) );
	
	//Test: delete_byID(Connection con,int id) Pass!
	//System.out.print( BookDao.delete_byID(con, 18)); //delete testBook1
	
	//Test: getBooksList()   Pass!
	//ArrayList<Books> aList = null;
	//aList = BookDao.getBooksList(con);
				 
//	for(Books aBook : aList) {
//		System.out.println(aBook.getBookName());
//	}
	
//	//Test: Object[] getArrayOfOneBook(Connection con,int bookID)
//	Object[] testArray = BookDao.getArrayOfOneBook(con,4);
//	for(int i=0; i<5; i++) {
//		System.out.println(testArray[i]);
//	}	
	//Test: getBookQuantity(Connection con,String name) Pass!
   //System.out.println(BookDao.getBookQuantity(con,"Harry Potter 1"));
	
	//Test: update_borrowTime(Connection con,String time,int id)
   //System.out.println(BookDao.update_borrowTime(con,"8/8/22",16));
  
  }
	
}//Nov 22
