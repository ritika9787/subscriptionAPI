package com.gymondo.rest.subscriptionAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.gymondo.rest.subscriptionAPI.entity.Product;
import com.gymondo.rest.subscriptionAPI.entity.SubscriptionPlan;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SubscriptionPlanRepositoryTest {
	
	@Autowired
	private SubscriptionPlanRepository planRepo;

    @Autowired
    private TestEntityManager entityManager;

	@Test
	public void testFetchSingleProduct() {
		Product product = new Product("Baby Shampoo", "Cetaphil", 10.5F);
		entityManager.persist(product);
		entityManager.persist(new SubscriptionPlan("plan1", 50F, true, product, 365, false));
	     
		SubscriptionPlan plan = planRepo.findOne(1);
	    assertThat(plan.getName()).isEqualTo("plan1");
	}

}
