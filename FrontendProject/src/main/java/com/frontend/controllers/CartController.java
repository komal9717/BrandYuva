package com.frontend.controllers;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.CartDao;
import com.backend.daos.ItemDao;
import com.backend.daos.OrderDao;
import com.backend.daos.ProductDao;
import com.backend.daos.UserDao;
import com.backend.modals.Address;
import com.backend.modals.Cart;
import com.backend.modals.Item;
import com.backend.modals.Order;
import com.backend.modals.Product;
import com.backend.modals.User;

@Controller
public class CartController {
	
private String userEmail;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ItemDao itemDao;
	
	User userObj;
	 

	
	@ModelAttribute
	public void getUserEmail(HttpServletRequest request){
		Principal p=request.getUserPrincipal();
		userEmail=p.getName();
		userObj=userDao.getUserById(userEmail);
	}
	
	
	@RequestMapping(value="/addToCart/{productId}",method=RequestMethod.GET)
	public String addToCart(@PathVariable("productId")int productId){
		Cart cartObj=cartDao.getCartByCustomer(userEmail);
		if(cartObj==null){
			System.out.println("Cart doesnt exist");
			cartObj=new Cart();
			cartObj.setUser(userDao.getUserById(userEmail));
			
			cartDao.addCart(cartObj);
			System.out.println("Cart created succesfully");
		}
		else {
			System.out.println("Cart exist already");
		}

		Product pObj=productDao.getProductById(productId);
		Item itemObj=itemDao.getItemByProductIdAndCustomerId(pObj.getProductId(),userEmail);
		
		if(itemObj==null)
		{
		itemObj=new Item();
		itemObj.setCart(cartObj);
		itemObj.setCustomerId(userEmail);
		itemObj.setPrice(pObj.getPrice());
		itemObj.setProduct(pObj);
		itemObj.setQuantity(1);
		
		itemDao.addItem(itemObj);
		System.out.println("Item added in cart");
		}
		else {
			itemDao.increaseQuantity(itemObj.getItemId());
			System.out.println("Quantity Updated Succesfully");
		}
		//return "redirect:/viewCart?cartId="+cartObj.getCartId();
        return "redirect:/viewCart";
	}
	
	@Autowired
	HttpSession session;

	@RequestMapping(value="viewCart",method=RequestMethod.GET)
	public ModelAndView getCart(@RequestParam(name="addrId",required=false)Integer addressId){
		
		Cart cartObj=cartDao.getCartByCustomer(userEmail);
		if(cartObj!=null){
			int count=0;
			Collection<Item> items=cartObj.getItems();
			for(Item it:items){
				count=count+it.getQuantity();
			}
			session.setAttribute("itemsCount",count);
			
		}
		
		
		if(cartObj==null){
			ModelAndView mv=new ModelAndView("Cart");
			mv.addObject("msg","Dear Customer , You cart is empty..");
			return mv;
		}
		else {
		int cartId=cartObj.getCartId();
		Collection<Item> itemslist=itemDao.getItemsListByCart(cartId);
		
		double sum=0;
		
		for(Item item:itemslist){
			sum=sum+(item.getPrice()*item.getQuantity());
		}
		
		ModelAndView mv=new ModelAndView("Cart");
		mv.addObject("cartItems",itemslist);
		mv.addObject("totalCost",sum);
		if(addressId!=null){
			mv.addObject("addressObj",userDao.getAddressById(addressId));
		}
		    session.setAttribute("totalCost",sum);
		    session.setAttribute("addressId",addressId);
			
			
		return mv;

		}
	}
	
	
	@RequestMapping(value="increaseQuantity/{itemId}/{productId}",method=RequestMethod.GET)
	public String increase(@PathVariable("itemId") int itemId, @PathVariable("productId") int productId , ModelMap map){
		Product pObj=productDao.getProductById(productId);
		int totalProducts=pObj.getQuantity();
		Item itemObj=itemDao.getItemById(itemId);
		if(totalProducts > itemObj.getQuantity()) {
			itemDao.increaseQuantity(itemId);
		}
		else {
			System.out.println("Reached Out of stock");
			map.addAttribute("status","Out of Stock");
		}
		
		return "redirect:/viewCart";
	}
	
	@RequestMapping(value="decreaseQuantity/{itemId}",method=RequestMethod.GET)
	public String decrease(@PathVariable int itemId){
		
		Item item=itemDao.getItemById(itemId);
		if(item.getQuantity()>1) {
			itemDao.decreaseQuantity(itemId);
		}
		else {
			itemDao.deleteItem(itemId);
		}
		
		return "redirect:/viewCart";
	}
	
	@RequestMapping(value="getAddressPage",method=RequestMethod.GET)
	public ModelAndView getAddressPage(){
		Set<Address> addrs=userObj.getAddresses();
		
		ModelAndView mv=new ModelAndView("AddressPage");
		if(addrs.size()!=0){
		mv.addObject("addressList",addrs);
		mv.addObject("operation","Address Not Null");
		}
		
		mv.addObject("addressObj",new Address());
		return mv;
	}

	/*The @ModelAttribute annotation can be used in two scenarios,Firstly, it can be used to inject data ,Secondly, it can be used to read data
	  from an existing model assigning. */
	@RequestMapping(value="addAddress",method=RequestMethod.POST)
	public String addAsddress(@ModelAttribute("addressObj")Address addressObj){

	addressObj.setUserObj(userObj);
	userDao.addAddress(addressObj);
	return "redirect:getAddressPage";	
	}
	
	@RequestMapping(value="/confirmationPage",method=RequestMethod.GET)
	public String showConfirmationPage(@RequestParam("address")int addressId ,ModelMap map ){
		System.out.println("I m show confirmation page");
		map.addAttribute("");
		
		return "redirect:viewCart?addrId="+addressId;
	}
	
	
	
	
	
	@RequestMapping(value="deleteAddress/{addrId}",method=RequestMethod.GET)
	public ModelAndView deleteAddress(@PathVariable("addrId") int addrId){
		
		Address addObj=userDao.getAddressById(addrId);
		userDao.deleteAddress(addObj);
		
		
		Set<Address> addr=userObj.getAddresses();
		
		ModelAndView mv=new ModelAndView("redirect:/getAddressPage");
		mv.addObject("msg","Address deleted Succesfully..");
		
			return mv;
	}
	
	
	@RequestMapping(value="updateAddress/{addrId}",method=RequestMethod.GET)
	public ModelAndView getUpdateAddressForm(@PathVariable("addrId") int addrId){
		
		Address addObj=userDao.getAddressById(addrId);
		
		ModelAndView mv=new ModelAndView("UpdateAddressPage");
		mv.addObject("userObj",userObj);
		mv.addObject("addrObj", addObj);
		return mv;	
	}

	@RequestMapping(value="/updateAddressProcess",method=RequestMethod.POST)
	public ModelAndView updateAddressProcess(@Valid @ModelAttribute("userObj")Address addrObj,BindingResult result ){

		if(result.hasErrors()){
			ModelAndView mv=new ModelAndView("AddressPage");
			mv.addObject("addressObj",addrObj);
			return mv;
		}
		else {
		userDao.updateAddress(addrObj);
		
		ModelAndView mv=new ModelAndView("AddressPage");
		mv.addObject("msg","Address Updated Succesfully");
		//mv.addObject("userList",userDao.getAllCategories());
		
		return mv;
		}
	}

	
	@RequestMapping(value="/makePayment",method=RequestMethod.GET)
	public ModelAndView ShowpaymentPage() {
		ModelAndView mv  = new ModelAndView("PaymentPage");
		return mv;
			
	}
	
	
	
	@Autowired
	OrderDao orderDao;
	
	@RequestMapping(value="/makePaymentProcess",method=RequestMethod.GET)
	public String dopaymentProcess() {
		Order obj=new Order();
		obj.setOrderDate(new Date());
		obj.setUserEmail(userEmail);
		obj.setTotalAmount((double)session.getAttribute("totalCost"));
		obj.setAddressId((int)session.getAttribute("addressId"));
		orderDao.makeOrder(obj);
		
		Cart cObj=cartDao.getCartByCustomer(userEmail);
		Collection<Item> item=cObj.getItems();
			for(Item items:item) {
				Product pro=items.getProduct();
				pro.setQuantity(pro.getQuantity()-items.getQuantity());
				productDao.updateProduct(pro);
				
				
			}
		
		cartDao.deleteCart(cObj.getCartId());
		
		return "redirect:thankYou";
		
	}

	
	
	
	@RequestMapping(value="/thankYou",method=RequestMethod.GET)
	public ModelAndView thankyou() {
		ModelAndView mv = new ModelAndView("ThankYouPage");
		return mv;
	}
	
	
}


