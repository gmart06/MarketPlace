package fr.m2i.stage.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.m2i.stage.marketplace.domain.entity.Product;
import fr.m2i.stage.marketplace.domain.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product findById(Long id) {
		return productRepository.findOne(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}
}
