package dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Books;
import model.User;
import utility_public.DataBaseUtility;
import org.junit.Assert;
import org.junit.Test;


/**
 * 
 * @author 
 *
 */
public class BookDaoTest {

	DataBaseUtility dbUtil = new DataBaseUtility(); 
	Connection con = dbUtil.getCon(); 
	Books newBook = new Books("TestBook3", 0,"Lucy");
	
	@Test
	public void test_returnBook(Connection con,int id) {	
		Assert.assertEquals("TestBook2",BookDao.returnBook(con, 14).getBookName())
	}
	
	@Test
	public void test_getTotalNum(Connection con){
		Assert.assertEquals(15,BookDao.getTotalNum(con))
	
	}
	
	@Test
	public void test_delete_byID(Connection con,int id){
		Assert.assertEquals(1,BookDao.delete_byID(con, 15))
	
	}
	
	@Test
	public void test_add(Connection con,Books book){
		//More codes will be added
		Assert.assertEquals(1,BookDao.add(con, newBook))
	
	}

}//
