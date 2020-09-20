package com.gymondo.rest.subscriptionAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.gymondo.rest.subscriptionAPI.entity.Order;
import com.gymondo.rest.subscriptionAPI.entity.OrderDetails;
import com.gymondo.rest.subscriptionAPI.entity.Product;
import com.gymondo.rest.subscriptionAPI.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {
	
	@Autowired
    private OrderDetailRepository orderRepo;

    @Autowired
    private TestEntityManager entityManager;

	
	@Test
	public void saveOrder() {
		User user = new User("user1", "p@ssword", "test@gmail.com");
		Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
		Order order = new Order(10.5F, 3.2F,user);
		
		entityManager.persist(new OrderDetails(2, 5.2F, product, order));
	     
		OrderDetails detail = orderRepo.findOne(1);
	    assertThat(detail.getOrder()).isEqualTo(order);
	    assertThat(detail.getProduct()).isEqualTo(product);
	    assertThat(detail.getOrder().getUser()).isEqualTo(user);
	}

}
