package com.gymondo.rest.subscriptionAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gymondo.rest.subscriptionAPI.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
	
	//fetch the informations related to my subscription
	@Query(value = "Select * FROM subscriptions " +
            "WHERE user_id= :userId", nativeQuery = true)
	List<Subscription> findUserSubscriptions(@Param("userId") int userId);
	
}
