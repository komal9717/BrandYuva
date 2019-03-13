package com.backend.daos;

import java.util.List;

import com.backend.modals.Product;

public  interface ProductDao {
	
	public boolean addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(Product product);
	public Product getProductById(int pId);
	public List<Product> getAllProducts();
		
	
	public List<Product> getAllProductsById(int categoryId);

}
