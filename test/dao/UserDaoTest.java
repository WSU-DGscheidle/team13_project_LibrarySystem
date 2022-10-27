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
public class UserDaoTest {
	
	DataBaseUtility dbUtil = new DataBaseUtility(); 
	Connection con = dbUtil.getCon(); 
	User test_user = new User("Jim","123456");
	
	@Test
	public void test_login(Connection con,User user) {
		
		assertThat.(test_user , is(UserDao.login(con, new User("Jim","123456")) ));
	}

}//
