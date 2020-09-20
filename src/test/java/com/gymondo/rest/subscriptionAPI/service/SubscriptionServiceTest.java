package com.gymondo.rest.subscriptionAPI.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gymondo.rest.subscriptionAPI.entity.Order;
import com.gymondo.rest.subscriptionAPI.entity.OrderDetails;
import com.gymondo.rest.subscriptionAPI.entity.OrderPostRequest;
import com.gymondo.rest.subscriptionAPI.entity.OrderPostRequest.BuyProduct;
import com.gymondo.rest.subscriptionAPI.entity.Product;
import com.gymondo.rest.subscriptionAPI.entity.Subscription;
import com.gymondo.rest.subscriptionAPI.entity.SubscriptionPlan;
import com.gymondo.rest.subscriptionAPI.entity.SubscriptionPostRequest;
import com.gymondo.rest.subscriptionAPI.entity.User;
import com.gymondo.rest.subscriptionAPI.repository.OrderDetailRepository;
import com.gymondo.rest.subscriptionAPI.repository.ProductRepository;
import com.gymondo.rest.subscriptionAPI.repository.SubscriptionPlanRepository;
import com.gymondo.rest.subscriptionAPI.repository.SubscriptionRepository;
import com.gymondo.rest.subscriptionAPI.repository.UserRepository;
import com.gymondo.rest.subscriptionAPI.utils.SubscriptionStatus;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceTest {
	
    @InjectMocks
    private SubscriptionService service;
	
	@Mock
	SubscriptionPlanRepository planRepo;
	@Mock
	UserRepository userRepo;
	@Mock
	SubscriptionRepository subscriptionRepo;
	
    @Before
	public void createMocks() {
	    MockitoAnnotations.initMocks(this);

	}
	
    @SuppressWarnings("unchecked")
	@Test
    public void testPauseActiveSubscriptionWithoutTrialPeriod() {
    	Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			false);
    	    	
    	Subscription sub = new Subscription(null, null, "2020-09-20 10:50:15.592", "2021-09-20 10:50:15.592",
				 SubscriptionStatus.ACTIVE);
    	sub.setSubscriptionPlan(plan);  	
		Mockito.when(subscriptionRepo.findOne(Matchers.anyInt())).thenReturn(sub);
		
		List<Subscription> subList = new ArrayList<Subscription>();
		subList.add(sub);
		Mockito.when(subscriptionRepo.save(Matchers.anyList())).thenReturn(subList);
		
		ResponseEntity<?> response = service.pauseSubscription(1);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.hasBody());
    	
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testPauseActiveSubscriptionWithTrialPeriod() {
    	Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			true);
    	    	
    	Subscription sub = new Subscription("2020-09-20 10:50:15.592", "2020-10-20 10:50:15.592", "2020-10-21 10:50:15.592", "2021-10-21 10:50:15.592",
				 SubscriptionStatus.ACTIVE);
    	sub.setSubscriptionPlan(plan);  	
		Mockito.when(subscriptionRepo.findOne(Matchers.anyInt())).thenReturn(sub);
		
		List<Subscription> subList = new ArrayList<Subscription>();
		subList.add(sub);
		Mockito.when(subscriptionRepo.save(Matchers.anyList())).thenReturn(subList);
		
		ResponseEntity<?> response = service.pauseSubscription(1);
		
		assertEquals(HttpStatus.PRECONDITION_FAILED, response.getStatusCode());
    	
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testPausePausedSubscription() {
    	Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			false);
    	    	
    	Subscription sub = new Subscription(null, null, "2020-10-21 10:50:15.592", "2021-10-21 10:50:15.592",
				 SubscriptionStatus.PAUSED);
    	sub.setSubscriptionPlan(plan);  	
		Mockito.when(subscriptionRepo.findOne(Matchers.anyInt())).thenReturn(sub);
		
		List<Subscription> subList = new ArrayList<Subscription>();
		subList.add(sub);
		Mockito.when(subscriptionRepo.save(Matchers.anyList())).thenReturn(subList);
		
		ResponseEntity<?> response = service.pauseSubscription(1);
		
		assertEquals(HttpStatus.PRECONDITION_FAILED, response.getStatusCode());
    	
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testCancelSubscriptionWithoutTrialPeriod() {
    	Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			false);
    	    	
    	Subscription sub = new Subscription(null, null, "2020-10-21 10:50:15.592", "2021-10-21 10:50:15.592",
				 SubscriptionStatus.ACTIVE);
    	sub.setSubscriptionPlan(plan);  	
		Mockito.when(subscriptionRepo.findOne(Matchers.anyInt())).thenReturn(sub);
		
		List<Subscription> subList = new ArrayList<Subscription>();
		subList.add(sub);
		Mockito.when(subscriptionRepo.save(Matchers.anyList())).thenReturn(subList);
		
		ResponseEntity<?> response = service.cancelSubscription(1);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
    	
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testCancelSubscriptionWithTrialPeriod() {
    	Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			true);
    	    	
    	Subscription sub = new Subscription("2020-09-20 10:50:15.592", "2020-10-20 10:50:15.592", "2020-10-21 10:50:15.592", "2021-10-21 10:50:15.592",
				 SubscriptionStatus.ACTIVE);
    	sub.setSubscriptionPlan(plan);  	
		Mockito.when(subscriptionRepo.findOne(Matchers.anyInt())).thenReturn(sub);
		
		List<Subscription> subList = new ArrayList<Subscription>();
		subList.add(sub);
		Mockito.when(subscriptionRepo.save(Matchers.anyList())).thenReturn(subList);
		
		ResponseEntity<?> response = service.cancelSubscription(1);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
    	
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testUnPauseActiveSubscription() {
    	Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			true);
    	    	
    	Subscription sub = new Subscription("2020-09-20 10:50:15.592", "2020-10-20 10:50:15.592", "2020-10-21 10:50:15.592", "2021-10-21 10:50:15.592",
				 SubscriptionStatus.ACTIVE);
    	sub.setSubscriptionPlan(plan);  	
		Mockito.when(subscriptionRepo.findOne(Matchers.anyInt())).thenReturn(sub);
		
		List<Subscription> subList = new ArrayList<Subscription>();
		subList.add(sub);
		Mockito.when(subscriptionRepo.save(Matchers.anyList())).thenReturn(subList);
		
		ResponseEntity<?> response = service.unpauseSubscription(1);
		
		assertEquals(HttpStatus.PRECONDITION_FAILED, response.getStatusCode());
    	
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testUnpausePausedSubscription() {
    	Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			false);
    	    	
    	Subscription sub = new Subscription(null, null, "2020-10-21 10:50:15.592", "2021-10-21 10:50:15.592",
				 SubscriptionStatus.PAUSED);
    	sub.setSubscriptionPlan(plan);  	
		Mockito.when(subscriptionRepo.findOne(Matchers.anyInt())).thenReturn(sub);
		
		List<Subscription> subList = new ArrayList<Subscription>();
		subList.add(sub);
		Mockito.when(subscriptionRepo.save(Matchers.anyList())).thenReturn(subList);
		
		ResponseEntity<?> response = service.unpauseSubscription(1);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
    	
    }
    
	@Test
    public void testBuySubscriptionWithoutTrial() {	    
    	User user = new User("user3", "p@ssword123", "test3@gmail.com");
		Mockito.when(userRepo.findOne(Matchers.anyInt())).thenReturn(user);
		
		Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			false);
		Mockito.when(planRepo.findOne(Matchers.anyInt())).thenReturn(plan);
		
    	Subscription sub = new Subscription(null, null, "2020-10-21 10:50:15.592", "2021-10-21 10:50:15.592",
				 SubscriptionStatus.ACTIVE);
    	sub.setSubscriptionPlan(plan);
    	
		Mockito.when(subscriptionRepo.save(Matchers.any(Subscription.class))).thenReturn(sub);
		
		SubscriptionPostRequest request = new SubscriptionPostRequest(); 
		Subscription response = service.buySubscription(request);
		assertEquals(sub, response);
		
    }
	
	@Test
    public void testBuySubscriptionWithTrial() {	    
    	User user = new User("user3", "p@ssword123", "test3@gmail.com");
		Mockito.when(userRepo.findOne(Matchers.anyInt())).thenReturn(user);
		
		Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
    	SubscriptionPlan plan = new SubscriptionPlan("plan1", 52F, true, product, 90,
    			true);
		Mockito.when(planRepo.findOne(Matchers.anyInt())).thenReturn(plan);
		
    	Subscription sub = new Subscription("2020-09-20 10:50:15.592", "2020-10-20 10:50:15.592", "2020-10-21 10:50:15.592", "2021-10-21 10:50:15.592",
				 SubscriptionStatus.ACTIVE);
    	sub.setSubscriptionPlan(plan);
    	
		Mockito.when(subscriptionRepo.save(Matchers.any(Subscription.class))).thenReturn(sub);
		
		SubscriptionPostRequest request = new SubscriptionPostRequest(); 
		Subscription response = service.buySubscription(request);
		assertEquals(sub, response);
		
    }
    
    
	
}
