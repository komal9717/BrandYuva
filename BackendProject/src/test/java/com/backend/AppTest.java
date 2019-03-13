package com.backend;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.daos.CategoryDao;
import com.backend.modals.Category;


public class AppTest {
	
	static CategoryDao categoryDao;
	
	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(DBConfig.class);
		context.refresh();
		
		categoryDao=context.getBean("categoryDao",CategoryDao.class);
		
		
	}
	
	@Test
	@Ignore
	public void addCategoryTest() {
		Category cat=new Category();
		cat.setCategoryName("Mens Wear");
		cat.setCategoryDesc("Summer wear");
		
		System.out.println("categoryDao :"+categoryDao);
		boolean r=categoryDao.addCategory(cat);
		assertTrue("Category Not Added",r);
	}
	
	 @Test
	 @Ignore
	    public void fetchCategoryByIdTest(){
	    	Category obj=categoryDao.getCategoryById(1);
	    	System.out.println(obj);
	    	assertNotNull("Category doesnt exist", obj);
	    }
	
 
	@Test
	@Ignore
	public void updateCategoryTest() {
		Category cObj= categoryDao.getCategoryById(1);
		cObj.setCategoryDesc("Casual and Party Wear");
		boolean r=categoryDao.updateCategory(cObj);
		assertTrue("Category not updated",r);
		}
	
	@Test
	@Ignore
    public void deleteCategoryTest(){
    	Category cObj=categoryDao.getCategoryById(2);
    	
    	boolean r=categoryDao.deleteCategory(cObj);
    	assertTrue("Category not deleted", r);
    	
    }
    
    @Test
    @Ignore
    public void getAllCategories(){
    	List<Category> list=categoryDao.getAllCategories();
    	for(Category c:list){
    		System.out.println(c);
    	}
    	assertTrue("No Category Found", list.size()>0);
    }
	
}
