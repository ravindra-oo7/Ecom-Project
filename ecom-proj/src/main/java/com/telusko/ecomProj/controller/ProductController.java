package com.telusko.ecomProj.controller;

import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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


//---Get All Products-------------------------------------------------------------	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts()
	{
		return new ResponseEntity<>(proService.getAllProducts(),HttpStatus.OK);
	}

//---Get Product By ID-------------------------------------------------------------	

	@GetMapping("/product/{prodID}")
	public ResponseEntity<Product> getProduct(@PathVariable int prodID)
	{
		Product product = proService.getProductByID(prodID);
		
		if(product != null)
		{
			return new ResponseEntity<>(product,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
