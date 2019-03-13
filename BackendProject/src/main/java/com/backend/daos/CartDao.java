package com.backend.daos;

import com.backend.modals.Cart;

public interface CartDao {

    public boolean addCart(Cart cart);
	public Cart getCartByCustomer(String customerId);
    public boolean deleteCart(int cartId);
	
}
