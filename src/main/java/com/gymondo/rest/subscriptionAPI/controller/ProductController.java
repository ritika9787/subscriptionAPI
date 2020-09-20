package com.gymondo.rest.subscriptionAPI.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gymondo.rest.subscriptionAPI.entity.Product;
import com.gymondo.rest.subscriptionAPI.service.ProductService;

@RestController
@Transactional
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//fetch list of all products
	@GetMapping("/products")
	public List<Product> listAllProducts() {
	    return productService.listAllProducts();
	}
    
	//fetch a single product 
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
	    try {
	        Product product = productService.getProduct(id);
	        return new ResponseEntity<Product>(product, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	    }      
	}	

}
