package fr.m2i.stage.marketplace.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Catalog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="date_import")
	private Date creationDate;
	
	private String url;
	
	@OneToMany(mappedBy=("container"), cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	private Set<Product> products;
	
	@OneToOne
	@JoinColumn(name="id_merchant")
	private Merchant merchant;
	
	public Catalog() {
		this.products = new HashSet<>();
	}
	
	public Catalog(Set<Product> products, Date creationDate, String url) {
		this.products = products;
		this.creationDate = creationDate;
		this.url = url;
		this.products = new HashSet<>();
	}

	public Long getId() {
		return id;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Override
	public String toString() {
		return "Catalog [id=" + id + ", creationDate=" + creationDate + ", url=" + url + ", products=" + products + "]";
	}
}
