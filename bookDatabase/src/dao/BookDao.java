package dao;

import model.Book;

public interface BookDao {
	
	void save (Book book);

	void update(Book book, String[] params);

	void delete(Book book);

}
