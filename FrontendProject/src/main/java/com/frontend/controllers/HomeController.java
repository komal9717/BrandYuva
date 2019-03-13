package com.frontend.controllers;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.CategoryDao;
import com.backend.daos.UserDao;
import com.backend.modals.Cart;
import com.backend.modals.Category;
import com.backend.modals.Item;
import com.backend.modals.User;
import com.backend.daos.CartDao;

@Controller
public class HomeController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CartDao cartDao;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView getHomePage(){
		
		Principal p=request.getUserPrincipal();
		if(p==null) {
			System.out.println("principal is NUll");
			}
		else {
			String email=p.getName();
			User user=userDao.getUserById(email);
			
			session.setAttribute("userObj",user);
			
			Cart cart=cartDao.getCartByCustomer(email);
			if(cart!=null){
				int count=0;
				Collection<Item> items=cart.getItems();
				for(Item it:items){
					count=count+it.getQuantity();
				}
				session.setAttribute("itemsCount",count);
			}
			
		}
		
		/*Model-data,View-whatever we want to display*/
		List<Category> categoryList=categoryDao.getAllCategories();
		session.setAttribute("categoryList", categoryList);
		
		System.out.println("I m Home Page");
		
		ModelAndView mv=new ModelAndView("HomePage");
		mv.addObject("myData","Your Fashion,Our Passion");
		return mv;	
		
	}

}
