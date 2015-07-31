package fr.m2i.stage.marketplace.domain.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String reference;
	private String title;
	private String description;
	
	@Column(name="visible")
	private boolean isVisible;
	
	@ManyToOne
	@JoinColumn(name="id_catalog")
	private Catalog container;
	
	@ManyToOne
	@JoinColumn(name="id_category")
	private Category category;	
	
	@OneToMany(mappedBy=("product"), cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	private Set<ProductDetail> productDetails;
	
	public Product() {}	
	
	public Product(String reference, String title, String description, boolean isVisible, Set<ProductDetail> listProductDetail) {		
		this.reference = reference;
		this.title = title;
		this.description = description;
		this.isVisible = isVisible;
		this.productDetails = listProductDetail;
	}

	public Long getId() {
		return id;
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
	public Set<ProductDetail> getProductDetails() {
		return productDetails;
	}
	public void setListProductDetail(Set<ProductDetail> productDetails) {
		this.productDetails = productDetails;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", reference=" + reference + ", title=" + title + ", description=" + description
				+ ", isVisible=" + isVisible + ", category=" + category + ", productDetails=" + productDetails + "]";
	}
}