package io.github.brunoyillli.ecommerceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.github.brunoyillli.ecommerceapi.entity.State;

@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {

	List<State> findByCountryCode(@Param("code") String code);
}
