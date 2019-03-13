package com.backend;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.daos.ProductDao;
import com.backend.modals.Product;


public class AppTest1 {
	
static ProductDao productDao;
	
	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(DBConfig.class);
		context.refresh();
		
		productDao=context.getBean("productDao",ProductDao.class);
		
		System.out.println(productDao);
	}
	
	@Test
	public void addProductTest() {
		
		//System.out.println("I m add To Product");
		Product pro=new Product();
		pro.setProductId(2);
		pro.setProductName("Lenovo A6600 Plus");
		pro.setPrice(6299.00);
		pro.setQuantity(7);
		pro.setDescription("Lenovo A6600 Plus(Black)");
		pro.setCategoryId(1);
		pro.setSupplierId(9);
		//pro.setImgName1();
		
		System.out.println("productDao :"+productDao);
		boolean r=productDao.addProduct(pro);
		assertTrue("Product Not Added",r);
	}
	
	 @Test
	 @Ignore
	    public void fetchProductByIdTest(){
	    	Product obj=productDao.getProductById(1);
	    	System.out.println(obj);
	    	assertNotNull("Product doesnt exist", obj);
	    }
	
 
	@Test
	@Ignore
	public void updateProductTest() {
		Product pro= productDao.getProductById(1);
		pro.setProductName("Lenovo A6600 Plus");
		pro.setDescription("Lenovo A6600 Plus(Black)");
		pro.setPrice(6299.00);
		pro.setQuantity(7);
		//pro.setImgName1();
		boolean r=productDao.updateProduct(pro);
		assertTrue("Product not updated",r);
		}
	
	@Test
	@Ignore
    public void deleteProductTest(){
    	Product pro=productDao.getProductById(1);
    	
    	boolean r=productDao.deleteProduct(pro);
    	assertTrue("Product not deleted", r);
    	
    }
    
    @Test
    @Ignore
  
    public void getAllProducts(){
    	List<Product> list=productDao.getAllProducts();
    	for(Product c:list){
    		System.out.println(c);
    	}
    	assertTrue("No Product Found", list.size()>0);
    }

}
