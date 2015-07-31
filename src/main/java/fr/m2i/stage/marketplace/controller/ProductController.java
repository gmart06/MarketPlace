 package fr.m2i.stage.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.m2i.stage.marketplace.domain.entity.Category;
import fr.m2i.stage.marketplace.domain.entity.Product;
import fr.m2i.stage.marketplace.domain.entity.ProductDetail;
import fr.m2i.stage.marketplace.service.CategoryService;
import fr.m2i.stage.marketplace.service.ProductDetailService;
import fr.m2i.stage.marketplace.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {
	
	private CategoryService categoryService;
	private ProductService productService;
	private ProductDetailService productDetailService;

	@Autowired
	public ProductController(CategoryService categoryService, ProductService productService, ProductDetailService productDetailService) {
		this.categoryService = categoryService;
		this.productService = productService;
		this.productDetailService = productDetailService;
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getAllCategory(Model model){		
		
		// List<Category> categories = categoryService.findAll(); A utiliser en remplacement de la solution de contournement
		List<String> categoriesName1 = categoryService.findDistinctName1();		
		List<Product> products = productService.findAll();
		List<ProductDetail> productsDetails = productDetailService.findAll();
		
		model.addAttribute("categoriesName1", categoriesName1); //Solution de contournement
		model.addAttribute("products", products);
		model.addAttribute("productsDetails", productsDetails);
		return "/index";
	}		
}