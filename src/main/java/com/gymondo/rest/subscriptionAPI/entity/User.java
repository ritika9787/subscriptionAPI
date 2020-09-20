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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
@Table(name = "Users")
public class User extends Auditable<String> implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "user_id")
    private int userId;
    
    @Column(unique = true, length = 20)
    @NotNull
    private String username;
    
    @Column(unique = true, length = 20)
    @NotNull
    private String password;
    
    private String firstName;
    private String lastName;
    
    @Column(unique = true, length = 20)
    @NotNull
    private String email;
    
    @JsonIgnore @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
        )
        private Set<Order> orders;
    
    @JsonIgnore @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
        )     
    private Set<Subscription> subscriptions ;


	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	

	public User() {
		// TODO Auto-generated constructor stub
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public int getUserId() {
		return userId;
	} 	
	
    
}
