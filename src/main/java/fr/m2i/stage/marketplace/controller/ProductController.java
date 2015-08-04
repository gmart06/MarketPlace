package fr.m2i.stage.marketplace.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fr.m2i.stage.marketplace.domain.entity.Categories;
import fr.m2i.stage.marketplace.domain.entity.Product;
import fr.m2i.stage.marketplace.domain.entity.ProductDetail;
import fr.m2i.stage.marketplace.exception.ResourceNotFoundException;
import fr.m2i.stage.marketplace.service.CategoryService;
import fr.m2i.stage.marketplace.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	private CategoryService categoryService;
	private ProductService productService;


	@Autowired
	public ProductController(CategoryService categoryService, ProductService productService) {
		this.categoryService = categoryService;
		this.productService = productService;


	}

		
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getAllCategory(Model model){		
		

		List<Categories> categories = categoryService.findByHierarchy(1);
		model.addAttribute("categories", categories);
		List<Categories> category2 = categoryService.findByHierarchy(2);
		model.addAttribute("category2", category2);
		List<Categories> category3 = categoryService.findByHierarchy(3);
		model.addAttribute("category3", category3);

		List<Product> products = productService.findAll();
		model.addAttribute("products", products);

		return "/index";
	}

	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
	public String getProductByCatId(Model model, @PathVariable Long categoryId) {
	//	List<Categories> categories = categoryService.findAll();
		List<String> categoriesName1 = new ArrayList<String>();
		List<Categories> category1 = categoryService.findByHierarchy(1);
		model.addAttribute("categories", category1);
		List<Categories> category2 = categoryService.findByHierarchy(2);
		model.addAttribute("category2", category2);
		List<Categories> category3 = categoryService.findByHierarchy(3);
		model.addAttribute("category3", category3);
		model.addAttribute("categoriesName1", categoriesName1);

		try{
			List<Product> products = productService.findByCategoryId(categoryId);
		model.addAttribute("products", products);

		} catch (ResourceNotFoundException r){
		return null;
		}
			
		
		 return "/list/listByCatId";
		
	}
	@RequestMapping(value= "/addBasket", method =RequestMethod.POST)
	public String addNewArticleForm(@Valid ProductDetail productDetail, BindingResult bindingResult){	
		//methode de validation formulaire
		
		if(bindingResult.hasErrors()){ 
			
			return "/list/listByCatId";
		}
	//	articleService.add(article);
		
		return "/basket/addBasket";
		
	}
}