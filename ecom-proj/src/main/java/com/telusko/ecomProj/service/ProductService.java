package com.telusko.ecomProj.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.telusko.ecomProj.model.Product;
import com.telusko.ecomProj.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo proRepo;
	
//---Get All Products-----------------------------------------------	
	public List<Product> getAllProducts()
	{
		return proRepo.findAll();
		
	}

//---Get Product By ID----------------------------------------------
	public Product getProductByID(int prodID) 
	{
		return proRepo.findById(prodID).orElse(null);
	}

//---Add New Product -------------------------------------------------	
	
	public Product addProduct(Product product, MultipartFile imageFile) throws IOException 
	{
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		     
		return proRepo.save(product);
	}

//---Update Product By Id--------------------------------------------------
	
	public Product updateProduct(int prodId, Product product, MultipartFile imgFile) throws IOException 
	{
		product.setImageData(imgFile.getBytes());
		product.setImageName(imgFile.getName());
		product.setImageType(imgFile.getContentType());
		return proRepo.save(product);
		
	}

//---Update Product By Id--------------------------------------------------
	
	public void deleteProduct(int proId) 
	{
		proRepo.deleteById(proId);
	}
	

}
