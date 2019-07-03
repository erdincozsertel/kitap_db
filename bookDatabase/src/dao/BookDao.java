package dao;

import java.util.List;

import javax.servlet.ServletException;

import model.Book;

public interface BookDao {
	
	
	void save (Book book);

	void update(Book book);

	void delete(Integer bookId);
	
	Book getBook(Integer bookId);
		
	List<Book> getBookList() throws ServletException;

}
