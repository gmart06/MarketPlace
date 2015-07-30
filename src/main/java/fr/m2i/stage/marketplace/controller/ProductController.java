 package fr.m2i.stage.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.m2i.stage.marketplace.domain.entity.Category;
import fr.m2i.stage.marketplace.service.CategoryService;
import fr.m2i.stage.marketplace.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private CategoryService categoryService;
	private ProductService productService;

	@Autowired
	public ProductController(CategoryService categoryService, ProductService productService) {
		this.categoryService = categoryService;
		this.productService = productService;
	}
	
	/*
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getIndex() {
		System.out.println("***TEST***");
		return "/index";
	}
	*/
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String getAllCategory(Model model){		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);		
		return "/menu";
	}	
}
