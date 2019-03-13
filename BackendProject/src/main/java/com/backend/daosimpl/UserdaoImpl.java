
package com.backend.daosimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.backend.daos.UserDao;
import com.backend.modals.Address;
import com.backend.modals.User;


@Repository("userDao")
@Transactional
public class UserdaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public boolean addUsers(User user) {
		try {
			Session session=sessionFactory.getCurrentSession();
			user.setPassword(encoder.encode(user.getPassword()));
			session.save(user);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;
		
	}

	@Override
	public User getUserById(String email) {
		try {
			Session session=sessionFactory.getCurrentSession();
			User userObj=session.get(User.class,email);
			return userObj;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}
	
	
	
	
	@Override
	public boolean addAddress(Address addr) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.save(addr);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public boolean deleteAddress(Address addr) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.delete(addr);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;	}

	@Override
	public boolean updateAddress(Address addr) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.update(addr);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public Address getAddressById(int addressId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Address addrObj=session.get(Address.class,addressId);
			return addrObj;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<Address> getAllAddressforUser(String email) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Query<Address> q=session.createQuery("from Address where userObj.email=:x");
			q.setParameter("x", email);
			return q.getResultList();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}
}
