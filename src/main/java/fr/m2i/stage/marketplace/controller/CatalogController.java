package fr.m2i.stage.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.m2i.stage.marketplace.service.CatalogService;

@Controller
public class CatalogController {
	
	private CatalogService catalogService;

	@Autowired
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	public void toto() {
		
	}

}
