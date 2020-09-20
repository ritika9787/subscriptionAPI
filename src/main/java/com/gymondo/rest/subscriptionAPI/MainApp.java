package com.gymondo.rest.subscriptionAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.gymondo.rest.subscriptionAPI.entity.Product;
import com.gymondo.rest.subscriptionAPI.entity.SubscriptionPlan;
import com.gymondo.rest.subscriptionAPI.entity.User;
import com.gymondo.rest.subscriptionAPI.repository.ProductRepository;
import com.gymondo.rest.subscriptionAPI.repository.SubscriptionPlanRepository;
import com.gymondo.rest.subscriptionAPI.repository.UserRepository;

@SpringBootApplication
public class MainApp {
	
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

}

@Component
class CustomRunner implements CommandLineRunner{

	@Autowired
	private ProductRepository prodRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private SubscriptionPlanRepository planRepo;

	@Override
	public void run(String... args) throws Exception {
		//creating users
		User user1 = new User();
		user1.setFirstName("User1");
		user1.setEmail("testEmail1@gmail.com");
		user1.setPassword("test123");
		user1.setUsername("testUser1");

		userRepo.save(user1);
		
		User user2 = new User();
		user2.setFirstName("User2");
		user2.setEmail("testEmail2@gmail.com");
		user2.setPassword("test1234");
		user2.setUsername("testUser2");

		userRepo.save(user2);
		
		//creating products
		Product product1 = new Product();
		product1.setName("Baby Shampoo");
		product1.setBrand("Cetaphil");
		product1.setPrice(10.5F);
		
		prodRepo.save(product1);
		
		Product product2 = new Product();
		product2.setName("Chips");
		product2.setBrand("Lays");
		product2.setPrice(2.2F);
	
		prodRepo.save(product2);
		
		Product product3 = new Product();
		product3.setName("Musical toy");
		product3.setBrand("FisherPrice");
		product3.setPrice(12.5F);
				
		prodRepo.save(product3);
		
		//creating plan without trial period
		SubscriptionPlan plan1 = new SubscriptionPlan();
		plan1.setActive(true);
		plan1.setName("plan1");
		plan1.setPrice(50.2F);
		plan1.setSubscriptionDurationDays(90);;
		plan1.setTax(5.10F);
		plan1.setProduct(product1);
		
		planRepo.save(plan1);
		
		//creating plan with 1 month trial period
		SubscriptionPlan plan2 = new SubscriptionPlan();
		plan2.setActive(true);
		plan2.setName("plan2");
		plan2.setPrice(50.2F);
		plan2.setSubscriptionDurationDays(365);
		plan2.setTax(5.10F);
		plan2.setHasTrialPeriod(true);
		plan2.setProduct(product2);
		
		planRepo.save(plan2);

	}
}