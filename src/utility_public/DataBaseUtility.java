package utility_public;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This is an utility Class for connecting Database
 * @author Caihong
 *
 */

public class DataBaseUtility {
	private String dbUrl = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5520691";  
	private String dbUserName = "sql5520691";     //Database User Name
	private String dbPassword = "XBmgbLjYuM";  //Database Password
	private String jdbcName = "mysql.jdbc.driver";   //Java to MySQL Connector Driver

	
    /**
     * Creates a Connection object by using database URL, database username and password
     * for sending SQL statements to the database.
     * @return connection
     * @throws Exception
     */
	public Connection getCon()throws Exception{
		//Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;

	}
	
	/**
	 * Close connection
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null) { con.close();}
		
	}

//You can uncomment ti test the connection to the online database
//	public static void main(String[] args) {
//		DataBaseUtility dbUtil = new DataBaseUtility();
//		try {
//			dbUtil.getCon();
//			System.out.print("Connect Success!");
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.print("Fail to connect DataBase!");
//			
//
//		}
//		
//	}//main
	
}//
