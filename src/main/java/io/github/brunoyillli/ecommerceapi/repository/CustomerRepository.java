package io.github.brunoyillli.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brunoyillli.ecommerceapi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByEmail(String theEmail);
	
}
