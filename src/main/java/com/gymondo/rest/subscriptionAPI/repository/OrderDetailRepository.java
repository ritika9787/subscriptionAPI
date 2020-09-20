package com.gymondo.rest.subscriptionAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymondo.rest.subscriptionAPI.entity.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Integer>{

}
