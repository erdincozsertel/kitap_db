package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.Book;

public class BookDao implements Dao<Book> {
	//This will be database instead of list
	private List<Book> books = new ArrayList<>();
    
//    public BookDao() {
//        books.add(new Book("book Name", "book Writer", "book Publisher", "book Category"));
//                
//    }

	@Override
	public Optional<Book> get(long id) {
		//Anlamadim
		return Optional.ofNullable(books.get((int) id));	
		}

	@Override
	public List<Book> getAll() {
		//return all books in db
		return books;
	}

	@Override
	public void save(Book book) {
		//will move from BookRegister.java
		books.add(book);		
	}

	@Override
	public void update(Book book, String[] params) {
		book.setBookName(Objects.requireNonNull(
				params[0], "BookName cannot be null"));
		book.setBookWriter(Objects.requireNonNull(
				params[1], "Book Writer cannot be null"));
		book.setBookPublisher(Objects.requireNonNull(
				params[2], "Book Publisher cannot be null"));
		book.setBookCategory(Objects.requireNonNull(
				params[3], "Book Category cannot be null"));
			  books.remove(book);
              books.add(book);
				
	}

	@Override
	public void delete(Book book) {
		//This function will delete user from db

		books.remove(book);		
	}
     


}
