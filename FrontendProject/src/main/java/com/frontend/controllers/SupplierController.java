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

import com.backend.daos.SupplierDao;
import com.backend.modals.Supplier;

@Controller
public class SupplierController {
	
	
	@Autowired
	SupplierDao supplierdao;
	
	@RequestMapping(value="/addSupplier",method=RequestMethod.GET)
	public ModelAndView getSupplierForm(){
		ModelAndView mv=new ModelAndView("SupplierForm");
		mv.addObject("supplierObj",new Supplier());
		return mv;
	}
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private SupplierDao supplierDao;
	
	@RequestMapping(value="/addSupplierProcess",method=RequestMethod.POST)
	public ModelAndView addSupplierProcess(@Valid @ModelAttribute("supplierObj")Supplier supplierObj,BindingResult result){

		ModelAndView mv=null;
		if(result.hasErrors()){
			mv=new ModelAndView("SupplierForm");
		
		}

	else{
		mv=new ModelAndView("ViewAllSuppliers");
		supplierDao.addSupplier(supplierObj);
		mv.addObject("msg","Supplier Added Succesfully");
		mv.addObject("supplierList",supplierDao.getAllSuppliers());
		session.setAttribute("supplierListDao",supplierDao.getAllSuppliers());
	   } 
	   return mv;
	}
	@RequestMapping(value="viewAllSuppliers",method=RequestMethod.GET)
	public ModelAndView getAllSuppliers(){
		List<Supplier> suppliers=supplierDao.getAllSuppliers();
		ModelAndView mv=new ModelAndView("ViewAllSuppliers");
		mv.addObject("supplierList",suppliers);
		return mv;
	}
	
	@RequestMapping(value="deleteSupplier/{supId}",method=RequestMethod.GET)
	public ModelAndView deleteSupplier(@PathVariable int supId){
		
		Supplier supObj=supplierDao.getSupplierById(supId);
		supplierDao.deleteSupplier(supObj);
		
		
		List<Supplier> suppliers=supplierDao.getAllSuppliers();
		
		ModelAndView mv=new ModelAndView("ViewAllSuppliers");
		mv.addObject("msg","Supplier deleted Succesfully..");
		mv.addObject("supplierList",suppliers);
		return mv;
	}
	
	
	@RequestMapping(value="updateSupplier/{sId}",method=RequestMethod.GET)
	public ModelAndView getUpdateSupplierForm(@PathVariable int sId){
		
		Supplier supObj=supplierDao.getSupplierById(sId);
		
		ModelAndView mv=new ModelAndView("UpdateSupplierForm");
		mv.addObject("supplierList",supplierdao.getAllSuppliers());
		mv.addObject("supplierObj",supObj);
		return mv;	
	}

	@RequestMapping(value="/updateSupplierProcess",method=RequestMethod.POST)
	public ModelAndView updateSupplierProcess(@Valid @ModelAttribute("supplierObj")Supplier supplierObj,BindingResult result){

		if(result.hasErrors()){
			ModelAndView mv=new ModelAndView("UpdateSupplierForm");
			return mv;
		}
		else {
		supplierDao.updateSupplier(supplierObj);
		
		ModelAndView mv=new ModelAndView("ViewAllSuppliers");
		mv.addObject("msg","Supplier Updated Succesfully");
		mv.addObject("supplierList",supplierDao.getAllSuppliers());
		
		return mv;
	}
	

	
}
}

