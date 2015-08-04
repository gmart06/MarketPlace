package fr.m2i.stage.marketplace.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.m2i.stage.marketplace.domain.entity.Categories;
import fr.m2i.stage.marketplace.domain.repository.CategoryRepository;

@Controller
@RequestMapping("/menu")
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	private CategoryRepository cat1Repository;

	@Autowired
	public CategoryController(CategoryRepository cat1Repository) {
		this.cat1Repository = cat1Repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String menu(Model model) {
		// recuperer la liste des categories

//		List<Object[]> category1 = cat1Repository.findDistinctId1Name1();
//		model.addAttribute("category", category1);
//		List<Object[]> category2 = cat1Repository.findDistinctId1Name2();
//		model.addAttribute("category2", category2);
//		List<Object[]> category3 = cat1Repository.findDistinctId1Name3();
//		model.addAttribute("category3", category3);
		
		List<Categories> categories = cat1Repository.findByHierarchy(1);
		model.addAttribute("categories", categories);
		List<Categories> category2 = cat1Repository.findByHierarchy(2);
		model.addAttribute("category2", category2);
		List<Categories> category3 = cat1Repository.findByHierarchy(3);
		model.addAttribute("category3", category3);
		return "menuTop/navbar";
		

	}
}
