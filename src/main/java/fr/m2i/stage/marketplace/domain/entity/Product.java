package fr.m2i.stage.marketplace.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String reference;
	private String title;
	private String description;
	private boolean isVisible;
	
	private Catalog container;
	private Category category;	
	
	@OneToMany(mappedBy=("product"))
	private List<ProductDetail> listProductDetail;
	
	public Product() {}	
	
	public Product(String reference, String title, String description, boolean isVisible, List<ProductDetail> listProductDetail) {		
		this.reference = reference;
		this.title = title;
		this.description = description;
		this.isVisible = isVisible;
		this.listProductDetail = listProductDetail;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public List<ProductDetail> getListProductDetail() {
		return listProductDetail;
	}
	public void setListProductDetail(List<ProductDetail> listProductDetail) {
		this.listProductDetail = listProductDetail;
	}
	public Catalog getContainer() {
		return container;
	}
	public void setContainer(Catalog container) {
		this.container = container;
		// ajouter test du catalog vide
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
		// ajouter test d'une category vide
	}
}