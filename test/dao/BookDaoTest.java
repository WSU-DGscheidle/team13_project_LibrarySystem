package utility_public;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Books;
import model.User;
import utility_public.DataBaseUtility;
import org.junit.Test
import utility_public;
import org.junit.Assert;


public class StringUtilityTest {
	@Test
	public void test_isNull(String str ) {
		Assert.assertEquals(false,StringUtility.isNull("abc"))
		Assert.assertEquals(true,StringUtility.isNull())
	}
	
	@Test
	public void test_isEmpty(String str){
		Assert.assertEquals(true,StringUtility.isEmpty(""))
	}

}//
