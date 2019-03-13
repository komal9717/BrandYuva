package com.backend.daos;

import java.util.List;

import com.backend.modals.Category;

public interface CategoryDao {
	public  boolean addCategory(Category obj);
	public  boolean deleteCategory(Category obj);
	public  boolean updateCategory(Category obj);
	public  Category getCategoryById(int categoryId);
		
	
	public List<Category> getAllCategories();

}
