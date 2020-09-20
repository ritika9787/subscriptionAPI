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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@SuppressWarnings("serial")
@Entity
@Table(name = "Orders")
public class Order extends Auditable<String> implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "order_id")
    private int orderId;
  
    
    @NotNull
    private Float orderAmount;
    
    private Float orderTax;

    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

     @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
        )
        private Set<OrderDetails> orderDetails;
    
	
    public Order(Float orderAmount, Float orderTax, User user) {
		super();
		this.orderAmount = orderAmount;
		this.orderTax = orderTax;
		this.user = user;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Float getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Float getOrderTax() {
		return orderTax;
	}

	public void setOrderTax(Float orderTax) {
		this.orderTax = orderTax;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	} 

}
