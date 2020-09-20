package com.gymondo.rest.subscriptionAPI.entity;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
@Table(name = "Order_Details")
public class OrderDetails implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private int orderDetailId;
    
    @NotNull
    private int productQuantity;
    @NotNull
    private Float totalPrice;   
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
    
    @JsonIgnore @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

	public OrderDetails(int productQuantity, Float totalPrice, Product product, Order order) {
		super();
		this.productQuantity = productQuantity;
		this.totalPrice = totalPrice;
		this.product = product;
		this.order = order;
	}

	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int producQuantity) {
		this.productQuantity = producQuantity;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
