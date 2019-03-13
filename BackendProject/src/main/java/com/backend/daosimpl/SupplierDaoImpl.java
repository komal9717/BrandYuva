package com.backend.daosimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.daos.SupplierDao;
import com.backend.modals.Supplier;


@Repository("supplierDao")
@Transactional
public class SupplierDaoImpl implements SupplierDao {

	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public boolean addSupplier(Supplier obj) {
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

	@Override
	public boolean deleteSupplier(Supplier obj) {
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

	@Override
	public boolean updateSupplier(Supplier obj) {
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

	@Override
	public Supplier getSupplierById(int supplierId) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Supplier sObj=session.get(Supplier.class, supplierId);
			return sObj;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Supplier");
			return query.getResultList();
		  }
			catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

}
