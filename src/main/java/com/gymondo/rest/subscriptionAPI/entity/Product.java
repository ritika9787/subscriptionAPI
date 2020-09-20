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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
@Table(name = "Product")
public class Product extends Auditable<String> implements Serializable {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "product_id")
    private int productId;
    
    @NotNull
    private String name;
    
    private String description;
    private String brand;
    
    @NotNull
    private Float price;
    
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private SubscriptionPlan prodSubscriptionPlan;
    
    @JsonIgnore @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
        )
        private Set<OrderDetails> orderDetails;
    
    @JsonIgnore @OneToMany(
            mappedBy = "subscribedProduct",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
        )
        private Set<Subscription> subscription;
    
    

	public Product(String name, String brand, Float price) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	public Product() {
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public SubscriptionPlan getProdSubscriptionPlan() {
		return prodSubscriptionPlan;
	}

	public void setProdSubscriptionPlan(SubscriptionPlan prodSubscriptionPlan) {
		this.prodSubscriptionPlan = prodSubscriptionPlan;
	}

	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Set<Subscription> getSubscription() {
		return subscription;
	}

	public void setSubscription(Set<Subscription> subscription) {
		this.subscription = subscription;
	}
		
}
