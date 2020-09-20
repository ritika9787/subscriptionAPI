package com.gymondo.rest.subscriptionAPI.controller;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gymondo.rest.subscriptionAPI.entity.Order;
import com.gymondo.rest.subscriptionAPI.entity.OrderPostRequest;

import com.gymondo.rest.subscriptionAPI.service.OrderService;


@RestController
@Transactional
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	//buy a single product
	  @PostMapping("/buyProduct")
	  ResponseEntity<?> buyProduct(@RequestBody OrderPostRequest request) {	
		  Order order = orderService.buyProduct(request);
		    return new ResponseEntity<Order>(order, HttpStatus.OK);     
	  }
	
		

}
