package model;

public class Book {

	private Integer bookId;
	// this value will be auto generated by the database
	private String bookName;
	private String bookWriter;
	private String bookPublisher;
	private String bookCategory;

	public Book(String bookName, String bookWriter, String bookPublisher, String bookCategory) {
		super();
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookPublisher = bookPublisher;
		this.bookCategory = bookCategory;
	}

	public Book(Book book) {
		super();
		this.bookName = book.getBookName();
		this.bookWriter = book.getBookWriter();
		this.bookPublisher = book.getBookPublisher();
		this.bookCategory = book.getBookCategory();
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(int bookID) {
		this.bookId = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookWriter() {
		return bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

}
