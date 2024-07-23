package com.telusko.ecomProj.controller;

import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.ecomProj.model.Product;
import com.telusko.ecomProj.service.ProductService;

@RestController
@CrossOrigin 
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService proService;

//----------------------------------------------------------------	
	@RequestMapping("/")
	public String greet()
	{
		return "Entered into Api successfully";
	}

//---Get All Products-------------------------------------------------------------	
	
	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		return proService.getAllProducts();
	}

//---Get Product By ID-------------------------------------------------------------	

	@GetMapping("/product/{prodID}")
	public Product getProduct(@PathVariable int prodID)
	{
		return proService.getProductByID(prodID);
	}
	
}
