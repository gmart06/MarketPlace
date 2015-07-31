package fr.m2i.stage.marketplace.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.m2i.stage.marketplace.domain.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findAll();

	@Query(value="SELECT DISTINCT name1 FROM Category")	// Solution de contournement
	List<String> findDistinctName1();

	
	@Query(value="SELECT DISTINCT c.id1, c.name1 FROM Category c")
	List<Object[]> findDistinctId1Name1();
	

	@Query(value="SELECT DISTINCT c.id2, c.name2 FROM Category c")
	List<Object[]> findDistinctId1Name2();
	
	@Query(value="SELECT DISTINCT c.id3, c.name3 FROM Category c")
	List<Object[]> findDistinctId1Name3();
}