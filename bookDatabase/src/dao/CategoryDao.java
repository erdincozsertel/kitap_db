package dao;

import java.util.List;

import javax.servlet.ServletException;

import model.Category;

public interface CategoryDao {

	boolean save(Category category);

	void update(Category category, String[] params);

	void delete(Category category);

	List<Category> getCategoryList() throws ServletException;
	
	String getCategoryName(Integer categoryId);

}
