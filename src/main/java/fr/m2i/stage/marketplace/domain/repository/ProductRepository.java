package fr.m2i.stage.marketplace.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import fr.m2i.stage.marketplace.domain.entity.Categories;
import fr.m2i.stage.marketplace.domain.entity.Product;


public interface ProductRepository extends CrudRepository<Product, Long> {	
	@EntityGraph(value="Product.ProductDetails", type=EntityGraphType.LOAD)
	List<Product> findAll();
	@EntityGraph(value="Product.ProductDetails", type=EntityGraphType.LOAD)
	List<Product> findByCategoryId(long category);

	
}
