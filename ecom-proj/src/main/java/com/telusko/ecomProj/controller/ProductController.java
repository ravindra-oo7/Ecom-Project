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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

//---Add New Product -------------------------------------------------	
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product,
										@RequestPart MultipartFile imageFile)
	{
		try 
		{
			Product product1 = proService.addProduct(product,imageFile);
			return new ResponseEntity<>(product1,HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
