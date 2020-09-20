package com.gymondo.rest.subscriptionAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.gymondo.rest.subscriptionAPI.entity.Subscription;
import com.gymondo.rest.subscriptionAPI.entity.User;
import com.gymondo.rest.subscriptionAPI.utils.SubscriptionStatus;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SubscriptionRepositoryTest {
	
	@Autowired
	private SubscriptionRepository subscriptionRepo;

    @Autowired
    private TestEntityManager entityManager;

	@Test
	public void testSaveAndFindSubscription() {		
		entityManager.persist(new Subscription(null, null, "2020-09-20 10:50:15.592", "2021-09-20 10:50:15.592",
				 SubscriptionStatus.ACTIVE));
	     
	    Subscription sub = subscriptionRepo.findOne(1);
	    assertThat(sub.getSubscriptionStatus()).isEqualTo(SubscriptionStatus.ACTIVE);

	}
	
	@Test 
	public void testFindByUserId() {
		User user = new User("user3", "p@ssword123", "test3@gmail.com");     
		Subscription sub = new Subscription(null, null, "2020-09-20 10:50:15.592", "2021-09-20 10:50:15.592",
				 SubscriptionStatus.ACTIVE);
		
		int userId = entityManager.persist(user).getUserId();	
		sub.setUser(user);
				
		entityManager.persist(sub);
		
		List<Subscription> subscriptions = subscriptionRepo.findUserSubscriptions(userId);
		
		assertThat(subscriptions.size()).isGreaterThan(0);
		
	}

}
