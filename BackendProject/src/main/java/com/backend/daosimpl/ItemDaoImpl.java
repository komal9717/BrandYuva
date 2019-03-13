package com.backend.daosimpl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.daos.ItemDao;
import com.backend.modals.Item;

@Repository("itemDao")
@Transactional
public class ItemDaoImpl implements ItemDao {


	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addItem(Item item) 
	{
		
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.save(item);
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateItem(Item item) {
		
		
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.update(item);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteItem(int itemId) {
		
		
		try
		{
			
			Session session=sessionFactory.getCurrentSession();
			Item obj=session.get(Item.class, itemId);
			session.delete(obj);
			return true;
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Item getItemByProductIdAndCustomerId(int productId, String customerId) 
	{
		
		try
		{
			
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Item where productId=:x and customerId=:y");
			query.setInteger("x",productId);
			query.setString("y",customerId);
			List<Item> getItemsListByCart=query.list();
			List<Item> list=query.list();
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
	public List<Item> getItemsListByCart(int cartId) {
		
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Item where cart.cartId=:x");
			query.setInteger("x",cartId);
			List<Item> getItemsListByCart=query.list();
			return getItemsListByCart;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean increaseQuantity(int itemId)
	{
		
		try
		{
			
			Session session=sessionFactory.getCurrentSession();
			Item itemObj=session.get(Item.class,itemId);
			itemObj.setQuantity(itemObj.getQuantity()+1);
			session.merge(itemObj);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean decreaseQuantity(int itemId)
	{
		
		try
		{
			Session session=sessionFactory.getCurrentSession();
			Item itemObj=session.get(Item.class, itemId);
			itemObj.setQuantity(itemObj.getQuantity()-1);
			
			if(itemObj.getQuantity()==0){
				return false;
			}
			session.merge(itemObj);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Item getItemById(int itemId)
	{
		try {
			Session session=sessionFactory.getCurrentSession();
			Item itemObj=session.get(Item.class,itemId);
			return itemObj;
			
			}
	
		catch(Exception e)
		    {
				e.printStackTrace();
			}
		return null;
    }
		
	
	


}
