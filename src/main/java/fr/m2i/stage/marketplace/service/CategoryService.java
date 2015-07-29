package fr.m2i.stage.marketplace.service;

import org.springframework.stereotype.Service;

import fr.m2i.stage.marketplace.domain.entity.Category;
import fr.m2i.stage.marketplace.domain.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public Category findById(Long id) {
		return categoryRepository.findOne(id);
	}

}
