package com.gymondo.rest.subscriptionAPI.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymondo.rest.subscriptionAPI.entity.Order;
import com.gymondo.rest.subscriptionAPI.entity.OrderDetails;
import com.gymondo.rest.subscriptionAPI.entity.OrderPostRequest;
import com.gymondo.rest.subscriptionAPI.entity.OrderPostRequest.BuyProduct;
import com.gymondo.rest.subscriptionAPI.entity.Product;

import com.gymondo.rest.subscriptionAPI.entity.User;
import com.gymondo.rest.subscriptionAPI.repository.OrderDetailRepository;
import com.gymondo.rest.subscriptionAPI.repository.ProductRepository;
import com.gymondo.rest.subscriptionAPI.repository.UserRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
    private OrderDetailRepository orderRepo;
	@Autowired
    private UserRepository userRepo;
	@Autowired
	private ProductRepository productRepo;

    
	//buy a product 
	public Order buyProduct(OrderPostRequest request) {
		Order order = new Order();
		Set<OrderDetails> orderDetails = new HashSet<OrderDetails>();
		Float orderAmount = 0F;
		
		User user = userRepo.findOne(request.userId);
		order.setUser(user);
		order.setOrderTax(3.12F);	
		
		for(BuyProduct buyProd : request.products) {
			Product product = productRepo.findOne(buyProd.getProductId());
			OrderDetails details = new OrderDetails();
			
			int buyQuantity = buyProd.getProductQuantity();			
			details.setProductQuantity(buyQuantity);				
			Float totalProductPrice = buyQuantity * product.getPrice();
			details.setTotalPrice(totalProductPrice);
			details.setProduct(product);
						
			orderAmount += totalProductPrice;
			details.setOrder(order);
			
			orderDetails.add(details);			
		}

		order.setOrderAmount(orderAmount);
		order.setOrderDetails(orderDetails);
		
		List<OrderDetails> response = orderRepo.save(orderDetails);
	
		return response.size() > 0 ? response.get(0).getOrder() : null;
	}
    

}
