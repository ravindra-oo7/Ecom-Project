package com.telusko.ecomProj.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telusko.ecomProj.model.Product;
import com.telusko.ecomProj.service.ProductService;

@RestController
@CrossOrigin 
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService proService;
	
	@Autowired
	private ObjectMapper mapper;


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
			
//			Product product = mapper.readValue(productString, Product.class);
			
			Product product1 = proService.addProduct(product,imageFile);
			return new ResponseEntity<>(product1,HttpStatus.CREATED); 
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//---Get Image By Product Id--------------------------------------------------
	
	@GetMapping("/product/{productId}/image")
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId)
	{
		Product product = proService.getProductByID(productId);
		byte[] imageFile = product.getImageData();
		
		return ResponseEntity.ok()
								.contentType(MediaType.valueOf(product.getImageType()))
								.body(imageFile);
	}
	
//---Update Product By Id--------------------------------------------------
	
	@PutMapping("/product/{proId}")
	public ResponseEntity<String> updateProduct(@PathVariable int proId,
												@RequestPart Product product,
												@RequestPart MultipartFile imgFile)
	{			
		Product productUpdated;
		
		try 
		{
			productUpdated = proService.updateProduct(proId,product,imgFile);
		} 
		catch (IOException e) 
		{
			return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
		}
		if(productUpdated != null)
			return new ResponseEntity<>("Updated",HttpStatus.OK);
		else
			return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
				
	}
	
//---Delete Product By Id--------------------------------------------------
	   
	@DeleteMapping("/product/{proId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int proId)
	{
		Product product = proService.getProductByID(proId);
		if(product!=null)
		{
			proService.deleteProduct(proId);
			return new ResponseEntity<>("Product Deleted Successfully ",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Product Not Found!!!",HttpStatus.NOT_FOUND);
		}
	}

//---Search--------------------------------------------------------------
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
	
		List<Product> products = proService.searchProduct(keyword);
		
		return new ResponseEntity<>(products,HttpStatus.FOUND);
				
		
	}
	
}
