package com.backend.daosimpl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.ProductDao;
import com.backend.modals.Product;


@Repository("productDao")
@Transactional
public  class ProductDaoImpl implements ProductDao {


	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addProduct(Product product) {
      try{
    	  Session session=sessionFactory.getCurrentSession();
			session.save(product);
			return true;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
			
		}
	
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.update(product);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public boolean deleteProduct(Product product) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.delete(product);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;
		
	}

	@Override
	public Product getProductById(int pId) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Product pObj=session.get(Product.class, pId);
			return pObj;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	

	@Override
	public List<Product> getAllProductsById(int categoryId) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Product where categoryId=:x");
			query.setParameter("x",categoryId);
			return query.getResultList();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Product");
			return query.getResultList();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

}
