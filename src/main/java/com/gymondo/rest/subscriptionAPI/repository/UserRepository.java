package com.gymondo.rest.subscriptionAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymondo.rest.subscriptionAPI.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
