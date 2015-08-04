package fr.m2i.stage.marketplace.domain.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import fr.m2i.stage.marketplace.domain.entity.Categories;

public interface CategoryRepository extends CrudRepository<Categories, Long> {
	List<Categories> findAll();

	List<Categories> findByHierarchy(long hierarchy);

}