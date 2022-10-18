package model;


public class Books {

	private String bookName;
	private int bookID;
	private int isAvailable;    //if a book is available isAvailable == 1 , if not isAvailable == 0
	private String lendTo; 
	
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
	
	
	public String getBookName() {
		return bookName;
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
	
	
}//
