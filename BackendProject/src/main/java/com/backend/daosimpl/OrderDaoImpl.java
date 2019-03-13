package com.backend.daosimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.OrderDao;
import com.backend.modals.Order;


@Repository("OrderDao")
@Transactional
public class OrderDaoImpl implements OrderDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean makeOrder(Order orderObj) {
		try{
			Session session=sessionFactory.getCurrentSession();
			session.save(orderObj);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
	
	
	}

}
	  
		

