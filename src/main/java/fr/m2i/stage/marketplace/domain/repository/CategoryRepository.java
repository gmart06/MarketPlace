package fr.m2i.stage.marketplace.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.m2i.stage.marketplace.domain.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findAll();

	@Query(value="SELECT DISTINCT name1 FROM Category")	// Solution de contournement
	List<String> findDistinctName1();
}