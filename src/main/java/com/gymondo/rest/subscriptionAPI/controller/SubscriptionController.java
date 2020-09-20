package com.gymondo.rest.subscriptionAPI.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gymondo.rest.subscriptionAPI.entity.Subscription;
import com.gymondo.rest.subscriptionAPI.entity.SubscriptionPostRequest;
import com.gymondo.rest.subscriptionAPI.service.SubscriptionService;

@RestController
@Transactional
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subService;
	
	//fetch the following informations related to my subscription 
	@GetMapping("/{userId}/subscriptions")
	public List<Subscription> findUserSubscriptions(@PathVariable Integer userId) {
	    return subService.findUserSubscriptions(userId);
	}
	
	//pause and unpause my subscription
	@PutMapping("/pauseSubscription")
	public ResponseEntity<?> pauseSubscription(@RequestBody SubscriptionPostRequest request) {
		return subService.pauseSubscription(request.subscriptionId);
	}
	
	@PutMapping("/unpauseSubscription")
	public ResponseEntity<?> unpauseSubscription(@RequestBody SubscriptionPostRequest request) {
		return subService.unpauseSubscription(request.subscriptionId);
	}
	
	//cancel subscription
	@PutMapping("/cancelSubscription")
	public ResponseEntity<?> cancelSubscription(@RequestBody SubscriptionPostRequest request) {
		return subService.cancelSubscription(request.subscriptionId);
	}
	
	//buy a subscription product
	@PostMapping("/buySubscription")
	public ResponseEntity<?> buySubscription(@RequestBody SubscriptionPostRequest request) {	
	        Subscription sub = subService.buySubscription(request);
	        return new ResponseEntity<>(sub, HttpStatus.OK);
	 }

}
