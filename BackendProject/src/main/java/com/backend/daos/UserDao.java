package com.backend.daos;

import java.util.List;

import com.backend.modals.Address;
import com.backend.modals.User;

public interface UserDao {
	
	public boolean addUsers(User user);
    public User getUserById(String email);
    
    public boolean addAddress(Address addr);
    public boolean deleteAddress(Address addr);
    public boolean updateAddress(Address addr);
    public Address getAddressById(int addressId);
    public List<Address> getAllAddressforUser(String email);
    
}

