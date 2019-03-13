package com.backend.daosimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.daos.CartDao;
import com.backend.modals.Cart;

@Repository("cartDao")
@Transactional

public class CartDaoImpl implements CartDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addCart(Cart cart) {
		try
		{
			
			Session session=sessionFactory.getCurrentSession();
			session.save(cart);
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Cart getCartByCustomer(String customerId) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Cart where user.email=:x");
			query.setString("x", customerId);
			List<Cart> list=query.list();
			System.out.println("list : "+list);
			if(list.size()!=0){
			return list.get(0);
			}
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteCart(int cartId) {
		try
		{
			
			Session session=sessionFactory.getCurrentSession();
			Cart obj=session.get(Cart.class,cartId);
			session.delete(obj);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
