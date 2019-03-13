package com.backend.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.backend.modals.Product;

@Component
public class MyProductValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product p=(Product)target;
		if(p.getCategoryId()==0){
			errors.rejectValue("categoryId", "mykey1");
		}
		if(p.getPimage1().getSize()==0){
			errors.rejectValue("pimage1", "mykey2");
		}
		
	}

}
