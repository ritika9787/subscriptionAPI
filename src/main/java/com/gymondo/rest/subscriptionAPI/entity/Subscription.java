package com.gymondo.rest.subscriptionAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.gymondo.rest.subscriptionAPI.utils.SubscriptionStatus;

@SuppressWarnings("serial")
@Entity
@Table(name = "Subscriptions",
uniqueConstraints={
	    @UniqueConstraint(columnNames = {"user_id","product_id"}, name = "unique_constraint"),
	})
public class Subscription extends Auditable<String> implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "subscription_id")
    private int subscriptionId;
    
	private String trialStartDate;
	private String trialEndDate;
	
	@NotNull
	private String startDate;
	@NotNull
	private String endDate;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", referencedColumnName = "plan_id")
    private SubscriptionPlan subscriptionPlan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private Product subscribedProduct;
    
    @Enumerated(EnumType.ORDINAL)
    private SubscriptionStatus subscriptionStatus;
    
    

	public Subscription(String trialStartDate, String trialEndDate, String startDate, String endDate,
			SubscriptionStatus subscriptionStatus) {
		super();
		this.trialStartDate = trialStartDate;
		this.trialEndDate = trialEndDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.subscriptionStatus = subscriptionStatus;
	}

	public Subscription() {
		// TODO Auto-generated constructor stub
	}

	public String getTrialStartDate() {
		return trialStartDate;
	}

	public void setTrialStartDate(String trialStartDate) {
		this.trialStartDate = trialStartDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String string) {
		this.startDate = string;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String localDateTime) {
		this.endDate = localDateTime;
	}

	public SubscriptionPlan getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getSubscribedProduct() {
		return subscribedProduct;
	}

	public void setSubscribedProduct(Product subscribedProduct) {
		this.subscribedProduct = subscribedProduct;
	}

	public SubscriptionStatus getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public String getTrialEndDate() {
		return trialEndDate;
	}

	public void setTrialEndDate(String trialEndDate) {
		this.trialEndDate = trialEndDate;
	}
    
    

}
