package model;


public class Book {

	private String bookName;
	private String bookID;
	private boolean isAvailable;
	private String lendTo; 
	
	//non-parameter constructor
	public Book() {
		super();
	}
	
	public Book(String bookName,String bookID, boolean isAvailable,String lendTo) {
		this.bookName = bookName;
		this.bookID = bookID; 
		this.isAvailable = isAvailable;
		this.lendTo = lendTo;			
	}
	
	//getter & setter(Alt+Shift+S)
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getLendTo() {
		return lendTo;
	}

	public void setLendTo(String lendTo) {
		this.lendTo = lendTo;
	}
	
	
}//
