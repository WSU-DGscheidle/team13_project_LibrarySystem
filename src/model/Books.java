package model;


/**
 * This is a class for book,it has 5 member data and some getter and setter methods.
 * @author Caihong
 * 
 */

public class Books {

	private String bookName;
	private int bookID;
	private int isAvailable;    //if a book is available isAvailable == 1 , if not isAvailable == 0
	private String lendTo; 
	private String borrowTime;
	private String dueDate;
	
	//non-parameter constructor
	public Books() {
		super();
	}
	
	public Books(String bookName,int bookID, int isAvailable,String lendTo) {
		this.bookName = bookName;
		this.bookID = bookID; 
		this.isAvailable = isAvailable;
		this.lendTo = lendTo;			
	}
	
	public Books(String bookName, int isAvailable,String lendTo) {
		this.bookName = bookName;
		this.isAvailable = isAvailable;
		this.lendTo = lendTo;			
	}
	
	public String getBookName() {
		return bookName;
	}

	public String getBorrowTime() {
		return borrowTime;
	}
	
	public void setBorrowTime(String time) {
		this.borrowTime = time;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getAvailable() {
		return isAvailable;
	}

	public void setAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getLendTo() {
		return lendTo;
	}

	public void setLendTo(String lendTo) {
		this.lendTo = lendTo;
	}
	
	public String getdueDay() {
		return dueDate;
	}

	public void setdueDay(String dueDate) {
		this.dueDate = dueDate;
	}
}//
