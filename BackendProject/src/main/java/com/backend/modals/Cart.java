package com.backend.modals;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CartTable")
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int cartId;
	
	@OneToOne
	private User user;

	
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.EAGER,mappedBy="cart")
	Collection<Item> items=new ArrayList<>();


	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Collection<Item> getItems() {
		return items;
	}


	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	

}
