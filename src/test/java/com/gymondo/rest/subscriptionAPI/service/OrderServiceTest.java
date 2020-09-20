package com.gymondo.rest.subscriptionAPI.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.gymondo.rest.subscriptionAPI.entity.Order;
import com.gymondo.rest.subscriptionAPI.entity.OrderDetails;
import com.gymondo.rest.subscriptionAPI.entity.OrderPostRequest;
import com.gymondo.rest.subscriptionAPI.entity.OrderPostRequest.BuyProduct;
import com.gymondo.rest.subscriptionAPI.entity.Product;
import com.gymondo.rest.subscriptionAPI.entity.User;
import com.gymondo.rest.subscriptionAPI.repository.OrderDetailRepository;
import com.gymondo.rest.subscriptionAPI.repository.ProductRepository;
import com.gymondo.rest.subscriptionAPI.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
	
    @InjectMocks
    private OrderService service;
	
	@Mock
	OrderDetailRepository orderRepo;
	@Mock
	UserRepository userRepo;
	@Mock
	ProductRepository productRepo;
	
    @Before
	public void createMocks() {
	    MockitoAnnotations.initMocks(this);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testBuySingleProduct() {		
		User user = new User("user1", "p@ssword", "test@gmail.com");
		Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
		
		Mockito.when(userRepo.findOne(Matchers.anyInt())).thenReturn(user);
		Mockito.when(productRepo.findOne(Matchers.anyInt())).thenReturn(product);
		
		OrderPostRequest request = new OrderPostRequest();
		request.setUserId(1);
		
		List<BuyProduct> reqProd = new ArrayList<BuyProduct>();
		BuyProduct prod = new BuyProduct();
		prod.setProductId(1);
		prod.setProductQuantity(2);
		reqProd.add(prod);
		request.setProducts(reqProd);
		
		Order order = new Order(21F, 3.2F, user);
		OrderDetails details = new OrderDetails(2, 21F,  product,  order);
		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		orderDetails.add(details);
		
		Mockito.when(orderRepo.save(Matchers.anyList())).thenReturn(orderDetails);
		
		Order savedOrder = service.buyProduct(request);
		
		assertNotNull(savedOrder);
		assertEquals(user, savedOrder.getUser());

	}
	
}
