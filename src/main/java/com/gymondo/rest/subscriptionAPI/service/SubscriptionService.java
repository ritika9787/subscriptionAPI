package com.gymondo.rest.subscriptionAPI.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gymondo.rest.subscriptionAPI.entity.Subscription;
import com.gymondo.rest.subscriptionAPI.entity.SubscriptionPlan;
import com.gymondo.rest.subscriptionAPI.entity.SubscriptionPostRequest;
import com.gymondo.rest.subscriptionAPI.entity.User;
import com.gymondo.rest.subscriptionAPI.repository.SubscriptionPlanRepository;
import com.gymondo.rest.subscriptionAPI.repository.SubscriptionRepository;
import com.gymondo.rest.subscriptionAPI.repository.UserRepository;
import com.gymondo.rest.subscriptionAPI.utils.Constants;
import com.gymondo.rest.subscriptionAPI.utils.SubscriptionStatus;

@Service
@Transactional
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepo;
	@Autowired
    private UserRepository userRepo;
	@Autowired
	private SubscriptionPlanRepository planRepo;
	
	//fetch the informations related to my subscription
	public List<Subscription> findUserSubscriptions(int userId){
		return subscriptionRepo.findUserSubscriptions(userId);
	}
	
	//pause Subscription
	public ResponseEntity<?> pauseSubscription(int subscriptionId) {
	    try {
			Subscription sub = subscriptionRepo.findOne(subscriptionId);
			
			//During the trial I am not able to pause my subscription
			if(!sub.getSubscriptionPlan().isHasTrialPeriod() & 
					sub.getSubscriptionStatus().equals(SubscriptionStatus.ACTIVE)){
				sub.setSubscriptionStatus(SubscriptionStatus.PAUSED);
				subscriptionRepo.save(sub);
		        return new ResponseEntity<>(sub, HttpStatus.OK);
			}			
	        return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
	    } catch (NoResultException e) {
	        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }  
	}
	
	//unpause Subscription
	public ResponseEntity<?> unpauseSubscription(int subscriptionId) {
	    try {
			Subscription sub = subscriptionRepo.findOne(subscriptionId);
			if(sub.getSubscriptionStatus().equals(SubscriptionStatus.PAUSED)) {
				sub.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
				subscriptionRepo.save(sub);
		        return new ResponseEntity<>(sub, HttpStatus.OK);
			}

	        return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);

	    } catch (NoResultException e) {
	        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }  
	}
	
	//cancel Subscription
	public ResponseEntity<?> cancelSubscription(int subscriptionId) {
	    try {
			Subscription sub = subscriptionRepo.findOne(subscriptionId);
			sub.setSubscriptionStatus(SubscriptionStatus.CANCELLED);			
			subscriptionRepo.save(sub);
	        return new ResponseEntity<>(sub, HttpStatus.OK);
	    } catch (NoResultException e) {
	        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }  
	}
	
	//buy Subscription
	public Subscription buySubscription(SubscriptionPostRequest request){
		Subscription sub = new Subscription();
		User user = userRepo.findOne(request.userId);
		sub.setUser(user);
		
		SubscriptionPlan plan = planRepo.findOne(request.planId);				
		sub.setSubscribedProduct(plan.getProduct());		
		sub.setSubscriptionPlan(plan);
		
		sub.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT); 
		LocalDateTime startDate = LocalDateTime.now(); 
		if(plan.isHasTrialPeriod()) {
			LocalDateTime trialStartDate = LocalDateTime.now();
			LocalDateTime trialEndDate = trialStartDate.plusDays(Constants.TRIAL_PERIOD_DAYS);
			sub.setTrialStartDate(dtf.format(trialStartDate));
			sub.setTrialEndDate(dtf.format(trialEndDate));
			startDate = trialEndDate.plusDays(1);
		}
		LocalDateTime endDate = startDate.plusDays(plan.getSubscriptionDurationDays()); 
    
	    sub.setStartDate(dtf.format(startDate));
	    sub.setEndDate(dtf.format(endDate));
	    
	    return subscriptionRepo.save(sub);
	    
	}

}
