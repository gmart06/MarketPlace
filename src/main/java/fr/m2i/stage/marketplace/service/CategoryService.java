package fr.m2i.stage.marketplace.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.m2i.stage.marketplace.domain.entity.Categories;
import fr.m2i.stage.marketplace.domain.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public Categories findById(Long id) {
		return categoryRepository.findOne(id);
	}
	
	
	public List<Categories> findByHierarchy(long id) {
		return categoryRepository.findByHierarchy(id);
	}
	
	public List<Categories> findAll() {	
		return categoryRepository.findAll();
	}

}
