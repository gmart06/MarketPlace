package fr.m2i.stage.marketplace.service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.m2i.stage.marketplace.domain.entity.Category;
import fr.m2i.stage.marketplace.domain.repository.CategoryRepository;
import fr.m2i.stage.marketplace.importCatalog.ParseXML;

@Service
public class CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(ParseXML.class);

	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public Category findById(Long id) {

		return categoryRepository.findOne(id);
	}

	/**
	 * [id1, name1]
	 * @return
	 */
	public List<Object[]> findDistinctId1Name1() {

		return categoryRepository.findDistinctId1Name1();
	}
	/**
	 * [id2, name2]
	 * @return
	 */
	public List<Object[]> findDistinctId1Name2() {

		return categoryRepository.findDistinctId1Name2();
	}/**
	 * [id3, name3]
	 * @return
	 */
	public List<Object[]> findDistinctId1Name3() {

		return categoryRepository.findDistinctId1Name3();
	}
	public List<Category> findAll() {
	
		return categoryRepository.findAll();
	}

	public List<String> findDistinctName1() {		
		return categoryRepository.findDistinctName1();
	}	
}
