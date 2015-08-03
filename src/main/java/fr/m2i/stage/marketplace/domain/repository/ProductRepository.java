package fr.m2i.stage.marketplace.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.m2i.stage.marketplace.domain.entity.Categories;
import fr.m2i.stage.marketplace.domain.entity.Product;
import fr.m2i.stage.marketplace.domain.entity.ProductDetail;

public interface ProductRepository extends CrudRepository<Product, Long> {	
	List<Product> findAll();

	List<Product> findByCategory(Categories category);
}
