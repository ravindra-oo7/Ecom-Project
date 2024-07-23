package com.telusko.ecomProj.controller;

import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.ecomProj.model.Product;
import com.telusko.ecomProj.service.ProductService;

@RestController
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
}
