package fr.m2i.stage.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.m2i.stage.marketplace.domain.entity.Categories;
import fr.m2i.stage.marketplace.domain.entity.Product;
import fr.m2i.stage.marketplace.domain.entity.ProductDetail;
import fr.m2i.stage.marketplace.domain.repository.CategoryRepository;
import fr.m2i.stage.marketplace.domain.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Product findById(Long id) {
		return productRepository.findOne(id);
	}
	public List<Product> findByCategoryId(Long categoryid) {
		Categories category = categoryRepository.findOne(categoryid);
		if(category == null) {
			// TODO transform this exception into a business exception
			throw new RuntimeException("Not category for id " + categoryid );
		}
		return productRepository.findByCategory(category);
	}
	
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
}
