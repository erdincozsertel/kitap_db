package dao;

import java.util.List;

import javax.servlet.ServletException;

import model.Book;

public interface BookDao {
	
	
	void save (Book book);

	void update(Integer bookId, String[] params);

	void delete(Integer bookId);
	
	List<Book> getBookList() throws ServletException;

}
