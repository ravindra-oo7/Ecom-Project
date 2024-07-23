package com.telusko.ecomProj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	

}
