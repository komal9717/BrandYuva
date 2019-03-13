package com.backend.daos;

import java.util.List;

import com.backend.modals.Supplier;



public interface SupplierDao {
	
	public  boolean addSupplier(Supplier obj);
	public  boolean deleteSupplier(Supplier obj);
	public  boolean updateSupplier(Supplier obj);
	public  Supplier getSupplierById(int supplierId);
		
	
	public List<Supplier> getAllSuppliers();



}
