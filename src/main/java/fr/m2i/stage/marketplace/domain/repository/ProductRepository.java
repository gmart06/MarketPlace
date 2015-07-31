package fr.m2i.stage.marketplace.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.m2i.stage.marketplace.domain.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {	
	List<Product> findAll();
}
