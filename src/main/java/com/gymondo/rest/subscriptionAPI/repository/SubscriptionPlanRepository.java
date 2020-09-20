package com.gymondo.rest.subscriptionAPI.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.gymondo.rest.subscriptionAPI.entity.SubscriptionPlan;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Integer> {

}
