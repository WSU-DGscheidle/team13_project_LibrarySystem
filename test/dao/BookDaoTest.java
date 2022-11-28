package dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Books;
import model.User;
import utility_public.DataBaseUtility;
import java.util.ArrayList;
import org.junit.Test
import org.junit.Assert;

public class BookDaoTest {
	DataBaseUtility dbUtil = new DataBaseUtility(); 
	Connection con = dbUtil.getCon();
	
	@Test
	public void test_delete_byID() {
		Assert.assertEquals(1,BookDao.delete_byID(con, 21));
	}
	
	@Test
	public void test_ update_isAvailable() {
		Assert.assertEquals(1,BookDao.delete_byID(con, 21));
	}
	


}//Nov 23
