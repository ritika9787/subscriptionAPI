package com.gymondo.rest.subscriptionAPI.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
@Table(name = "Subscription_Plans", 
uniqueConstraints={
	    @UniqueConstraint(columnNames = {"subscriptionDurationDays", "product_id"}, name = "unique_duration"),
	    @UniqueConstraint(columnNames = {"price", "product_id"}, name = "unique_price")
	})
public class SubscriptionPlan extends Auditable<String> implements Serializable{
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "plan_id")
    private int planId;
    
    @NotNull
    private String name;
    
    private String description;
    
    @NotNull
    private Float price;
    
    private Float tax;
    private boolean isActive;
    
    @JsonIgnore @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
    
    @JsonIgnore @OneToMany(
            mappedBy = "subscriptionPlan",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
        )
        private Set<Subscription> subscriptions;
    
    @NotNull
    private int subscriptionDurationDays;  

    private boolean hasTrialPeriod;
       
    
	public SubscriptionPlan(String name, Float price, boolean isActive, Product product, int subscriptionDurationDays,
			boolean hasTrialPeriod) {
		super();
		this.name = name;
		this.price = price;
		this.isActive = isActive;
		this.product = product;
		this.subscriptionDurationDays = subscriptionDurationDays;
		this.hasTrialPeriod = hasTrialPeriod;
	}

	public SubscriptionPlan() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isHasTrialPeriod() {
		return hasTrialPeriod;
	}

	public void setHasTrialPeriod(boolean hasTrialPeriod) {
		this.hasTrialPeriod = hasTrialPeriod;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setTax(Float tax) {
		this.tax = tax;
	}
	
	public int getSubscriptionDurationDays() {
		return subscriptionDurationDays;
	}

	public void setSubscriptionDurationDays(int subscriptionDurationDays) {
		this.subscriptionDurationDays = subscriptionDurationDays;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
   
}
