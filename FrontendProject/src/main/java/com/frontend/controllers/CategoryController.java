package com.frontend.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.CategoryDao;
import com.backend.modals.Category;

@Controller
public class CategoryController {      
	
	@Autowired
	CategoryDao categoryDao;
/*
	The @RequestMapping annotation can be applied to class-level and/or method-level in a controller. The class-level annotation maps a 
	specific request path or pattern onto a controller.*/ 
	
	@RequestMapping(value="/addCategory",method=RequestMethod.GET)
	public ModelAndView getCategoryForm(){
	ModelAndView mv=new ModelAndView("CategoryForm");
	mv.addObject("categoryObj",new Category());
	return mv;
	}
	
	/*The HttpSession object is used for session management. A session contains information specific to a particular user across the whole application.
    You can store the user information into the session object by using setAttribute() method and later when needed this information can be fetched
    from the session.*/
	
	
	@Autowired
	HttpSession session;
	
	/*[ BindingResult ] is Spring's object that holds the result of the validation and binding and contains errors that may have occurred. 
	  The BindingResult must come right after the model object that is validated or else Spring will fail to validate the object and throw an 
	  exception.*/
	/* @Valid annotation is used to validate the method of the controller*/
	
	@RequestMapping(value="/addCategoryProcess",method=RequestMethod.POST)
	public ModelAndView addCategoryProcess(@Valid @ModelAttribute("categoryObj")Category categoryObj,BindingResult result){


		ModelAndView mv=null;
		if(result.hasErrors()){
			mv=new ModelAndView("CategoryForm");
		
		}
		else {
			mv=new ModelAndView("ViewAllCategories");
			categoryDao.addCategory(categoryObj);
			mv.addObject("msg","Category Added Succesfully");
			mv.addObject("categoryList",categoryDao.getAllCategories());
			session.setAttribute("categoryList",categoryDao.getAllCategories());
			
		}
		
		return mv;
	}
	
	@RequestMapping(value="viewAllCategories",method=RequestMethod.GET)
	public ModelAndView getAllCategories(){
		List<Category> categories=categoryDao.getAllCategories();
		ModelAndView mv=new ModelAndView("ViewAllCategories");
		mv.addObject("categoryList",categories);
		return mv;
	}
	
	@RequestMapping(value="deleteCategory/{catId}",method=RequestMethod.GET)
	public ModelAndView deleteCategory(@PathVariable int catId){
		
		Category catObj=categoryDao.getCategoryById(catId);
		categoryDao.deleteCategory(catObj);
		
		
		List<Category> categories=categoryDao.getAllCategories();
		
		ModelAndView mv=new ModelAndView("ViewAllCategories");
		mv.addObject("msg","Category deleted Succesfully..");
		mv.addObject("categoryList",categories);
		return mv;
	}
	
	
	@RequestMapping(value="updateCategory/{cId}",method=RequestMethod.GET)
	public ModelAndView getUpdateCategoryForm(@PathVariable int cId){
		
		Category catObj=categoryDao.getCategoryById(cId);
		
		ModelAndView mv=new ModelAndView("UpdateCategoryForm");
		mv.addObject("categoryObj",catObj);
		return mv;	
	}

	@RequestMapping(value="/updateCategoryProcess",method=RequestMethod.POST)
	public ModelAndView updateCategoryProcess(@Valid @ModelAttribute("categoryObj")Category categoryObj,BindingResult result){

		if(result.hasErrors()){
			ModelAndView mv=new ModelAndView("UpdateCategoryForm");
			return mv;
		}
		else {
		categoryDao.updateCategory(categoryObj);
		
		ModelAndView mv=new ModelAndView("ViewAllCategories");
		mv.addObject("msg","Category Updated Succesfully");
		mv.addObject("categoryList",categoryDao.getAllCategories());
		
		return mv;
		}
	}

	
}




