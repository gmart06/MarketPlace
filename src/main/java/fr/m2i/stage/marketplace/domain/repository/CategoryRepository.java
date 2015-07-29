package fr.m2i.stage.marketplace.domain.repository;

import org.springframework.data.repository.CrudRepository;

import fr.m2i.stage.marketplace.domain.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
}
