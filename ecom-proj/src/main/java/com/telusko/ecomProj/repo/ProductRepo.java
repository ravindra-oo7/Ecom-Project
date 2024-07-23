package com.telusko.ecomProj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.ecomProj.model.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> 
{

}
