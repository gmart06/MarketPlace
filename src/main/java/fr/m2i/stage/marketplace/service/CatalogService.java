package fr.m2i.stage.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.m2i.stage.marketplace.domain.repository.CatalogRepository;

@Service
public class CatalogService {
	
	private CatalogRepository catalogRepository;

	@Autowired
	public CatalogService(CatalogRepository catalogRepository) {
		this.catalogRepository = catalogRepository;
	}	

}
