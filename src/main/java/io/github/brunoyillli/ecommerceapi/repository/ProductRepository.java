package io.github.brunoyillli.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.github.brunoyillli.ecommerceapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
