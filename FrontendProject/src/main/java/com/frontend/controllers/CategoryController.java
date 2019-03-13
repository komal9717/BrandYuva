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

	@RequestMapping(value="/addCategory",method=RequestMethod.GET)
	public ModelAndView getCategoryForm(){
	ModelAndView mv=new ModelAndView("CategoryForm");
	mv.addObject("categoryObj",new Category());
	return mv;
	}
	
	
	@Autowired
	HttpSession session;
	
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




