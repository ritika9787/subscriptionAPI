package com.gymondo.rest.subscriptionAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymondo.rest.subscriptionAPI.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	

}
