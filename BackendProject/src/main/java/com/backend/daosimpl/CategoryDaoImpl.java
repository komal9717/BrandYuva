package com.backend.daosimpl;

import java.util.List;


import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.daos.CategoryDao;
import com.backend.modals.Category;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addCategory(Category obj) {
		try{
			Session session=sessionFactory.getCurrentSession();
			session.save(obj);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		
	}

	public boolean deleteCategory(Category obj) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.delete(obj);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;
		
	}

	public boolean updateCategory(Category obj) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.update(obj);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;
	
	}

	public Category getCategoryById(int categoryId) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Category cObj=session.get(Category.class, categoryId);
			return cObj;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	public List<Category> getAllCategories() {
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Category");
			return query.getResultList();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

}
