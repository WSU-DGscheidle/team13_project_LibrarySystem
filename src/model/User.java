package model;


/**
 * This is a class for User,it has 2 member data and some getter and setter methods.
 * @author Caihong
 * 
 */
public class User {

	private String userName;
	private String password;
	
	
	
	//non-parameter constructor
	public User() {
		 //Invoking the parent class constructor(in this case is the Object class)
		// with the usage of super() word
		super();		
	}
	
	//Constructor using parameters
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	//getter & setter  
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
