package com.gymondo.rest.subscriptionAPI.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymondo.rest.subscriptionAPI.entity.Product;
import com.gymondo.rest.subscriptionAPI.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
    private ProductRepository productRepo;
    
	//fetch list of all products
    public List<Product> listAllProducts() {
        return productRepo.findAll();
    }
     
    //fetch a single product 
    public Product getProduct(Integer id) {
        return productRepo.findOne(id);
    }

}
