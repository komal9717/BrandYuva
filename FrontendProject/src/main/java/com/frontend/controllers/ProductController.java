
package com.frontend.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.CategoryDao;
import com.backend.daos.ProductDao;
import com.backend.daos.SupplierDao;
import com.backend.modals.Product;
import com.backend.validators.MyProductValidator;


@Controller
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value="/addProduct",method=RequestMethod.GET)
	public ModelAndView getProductForm(){
	
		Product pro=new Product();
	
        ModelAndView mv=new ModelAndView("ProductForm");
		mv.addObject("productObj",pro);
		mv.addObject("categoryList",categoryDao.getAllCategories());
		mv.addObject("supplierList",supplierDao.getAllSuppliers());
		return mv;
	}
	
	@Autowired
	HttpSession session;
	
	@Autowired
	MyProductValidator productValidator;
	
	@RequestMapping(value="/addProductProcess",method=RequestMethod.POST)
	public ModelAndView addProcess(@Valid @ModelAttribute("productObj") Product productObj,BindingResult result){
	
		
		productValidator.validate(productObj,result);
		if(productObj.getPimage1().getSize()==0){
		result.rejectValue("pimage1","mykey2");
		}
		if(result.hasErrors()){
			ModelAndView mv=new ModelAndView("AddProductForm");
			return mv;
		}
		else{
		MultipartFile fileObj=productObj.getPimage1();
		String imgName=fileObj.getOriginalFilename();
		
		
		productObj.setImgName1(imgName);
		productDao.addProduct(productObj);
	
		uploadImage(fileObj);
		
		ModelAndView mv=new ModelAndView("ViewAllProducts");
		mv.addObject("msg","Product Added Succesfully");
		mv.addObject("productList",productDao.getAllProducts());
		return mv;
		}
	}
	
	@RequestMapping(value="/viewAllProducts",method=RequestMethod.GET)
	public ModelAndView viewAllProducts(){
		
		
		System.out.println(productDao.getAllProducts());
		ModelAndView mv=new ModelAndView("ViewAllProducts");
		mv.addObject("productList",productDao.getAllProducts());
		return mv;
	}
	
	@RequestMapping(value="/deleteProduct/{pId}",method=RequestMethod.GET)
	public ModelAndView deleteProductById(@PathVariable int pId){
		
		Product pro=productDao.getProductById(pId);
		productDao.deleteProduct(pro);
		
		ModelAndView mv=new ModelAndView("ViewAllProducts");
		mv.addObject("productList",productDao.getAllProducts());
		mv.addObject("msg","Product Deleted Succesfully");
		return mv;
	}
	
	@RequestMapping(value="/getAllProductsByCategory/{cId}",method=RequestMethod.GET)
	public ModelAndView getProductsByCategory(@PathVariable int cId){
		
		List<Product> productsList=productDao.getAllProductsById(cId);
		
		ModelAndView mv=new ModelAndView("ViewAllProductsByCategory");
		mv.addObject("productList",productsList);
		return mv;
	}
	
	@RequestMapping(value="updateProduct/{proId}",method=RequestMethod.GET)
	public ModelAndView getUpdateProductForm(@PathVariable int proId){
		Product proObj=productDao.getProductById(proId);
		
		ModelAndView mv=new ModelAndView("UpdateProductForm");
		mv.addObject("productObj",proObj);
		return mv;
	}
	
	@RequestMapping(value="updateProductProcess",method=RequestMethod.POST)
	public ModelAndView updateProductProcess(@Valid @ModelAttribute("productObj") Product productObj,BindingResult result){
		
		productValidator.validate(productObj,result);
		if(result.hasErrors()){
			ModelAndView mv=new ModelAndView("UpdateProductForm");
			return mv;
		}
		
		MultipartFile mFile=productObj.getPimage1();
		
		if(mFile.getSize()==0){
			
			int pId=productObj.getProductId();
			
			System.out.println("Product Id" +pId);
			Product p=productDao.getProductById(pId);
			String oldImageName=p.getImgName1();
			System.out.println(oldImageName);
			productObj.setImgName1(oldImageName);
			
		}
		
		else {
		productObj.setImgName1(mFile.getOriginalFilename());
		productDao.updateProduct(productObj);
		
		uploadImage(mFile);
		}
		ModelAndView mv=new ModelAndView("ViewAllProducts");
		mv.addObject("productList",productDao.getAllProducts());
		mv.addObject("msg","Product Updated Succesfully");
		
		return mv;
	}
	
	
	private void uploadImage(MultipartFile fileObj){
		String filePathString = session.getServletContext().getRealPath("/");
		System.out.println("filePathString : "+filePathString);
		
		try {
			
			byte[] imageBytes=fileObj.getBytes();
			
			String str=filePathString+"resources\\images\\";
			File file=new File(str);
			if(!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream fos=new FileOutputStream
					(filePathString+"resources\\images\\"+fileObj.getOriginalFilename());
			BufferedOutputStream bos= new BufferedOutputStream(fos);
	
			
			bos.write(imageBytes);
			bos.close();
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

