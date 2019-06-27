package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.Book;

public class BookDao implements Dao<Book> {
	private List<Book> books = new ArrayList<>();
    
    public BookDao() {
        books.add(new Book("book Name", "book Writer", "book Publisher", "book Category"));
                
    }

	@Override
	public Optional<Book> get(long id) {
		return Optional.ofNullable(books.get((int) id));	}

	@Override
	public List<Book> getAll() {
		return books;
	}

	@Override
	public void save(Book book) {
		books.add(book);		
	}

	@Override
	public void update(Book book, String[] params) {
//		user.setUserName(Objects.requireNonNull(
//				params[0], "Name cannot be null"));
//		user.setPassword(Objects.requireNonNull(
//				params[1], "Email cannot be null"));
//               
		
              books.add(book);
				
	}

	@Override
	public void delete(Book book) {
		books.remove(book);		
	}
     


}
