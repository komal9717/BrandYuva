package com.backend;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.daos.SupplierDao;
import com.backend.modals.Supplier;

public class AppTest2 {
	
	static SupplierDao supplierDao;
	
	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(DBConfig.class);
		context.refresh();
		
		supplierDao=context.getBean("supplierDao",SupplierDao.class);
		
		
	}

    @Test
    @Ignore
	public void addSupplierTest() {
		Supplier sup=new Supplier();
		sup.setSupplierId(1);
		sup.setSupplierName("Komal");
		sup.setSupplierAddress("A40,East Delhi");
		
		System.out.println("supplierDao :"+supplierDao);
		boolean r=supplierDao.addSupplier(sup);
		assertTrue("Supplier Not Added",r);
	}
	
    @Test
    @Ignore
    public void fetchSupplierByIdTest(){
    	Supplier obj=supplierDao.getSupplierById(1);
    	System.out.println(obj);
    	assertNotNull("Supplier doesnt exist", obj);
    }

    
    @Test
    
	public void updateSupplierTest() {
    	Supplier sObj= supplierDao.getSupplierById(9);
		sObj.setSupplierName("H&M");
		sObj.setSupplierAddress("G30,Uttam Nagar,Delhi");
		boolean r=supplierDao.updateSupplier(sObj);
		assertTrue("Supplier not updated",r);
		}
	
    @Test
    @Ignore
    public void deleteSupplierTest(){
    	Supplier cObj=supplierDao.getSupplierById(1);
    	
    	boolean r=supplierDao.deleteSupplier(cObj);
    	assertTrue("Supplier not deleted", r);
    	
    }
    
    @Test
    @Ignore
    
    public void getAllSuppliers(){
    	List<Supplier> list=supplierDao.getAllSuppliers();
    	for(Supplier c:list){
    		System.out.println(c);
    	}
    	assertTrue("No Supplier Found", list.size()>0);
    }
    
}
