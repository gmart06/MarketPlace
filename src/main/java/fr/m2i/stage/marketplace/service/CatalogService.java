package fr.m2i.stage.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.m2i.stage.marketplace.domain.entity.Catalog;
import fr.m2i.stage.marketplace.domain.repository.CatalogRepository;

@Service
public class CatalogService {
	
	private CatalogRepository catalogRepository;

	@Autowired
	public CatalogService(CatalogRepository catalogRepository) {
		this.catalogRepository = catalogRepository;
	}	
	
	@Transactional
	public void add(Catalog catalog) {
//	CatalogRepository.save(catalog); //le . save est valable pour insert comme update
	
	}

}
