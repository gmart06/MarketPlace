package fr.m2i.stage.marketplace.domain.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product_detail")
public class ProductDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String sku;
	private int stock;
	private double price;
	private double ecotax;
	private String ean;
	private String description;
	private String image_url;
	private String size;
	private String color;
	private double weight;
	
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinTable(name="product_detail_delivery"
			 , joinColumns=@JoinColumn(name="id_product_detail", referencedColumnName="id")
			 , inverseJoinColumns=@JoinColumn(name="id_delivery", referencedColumnName="id"))
	private Set<Delivery> deliveries;
	
	public ProductDetail() {}
	
	public ProductDetail(String sku, int stock, double price, double ecotax, String ean, String description, String image_url,
			String size, String color, int weight, Set<Delivery> deliveries) {		
		this.sku = sku;
		this.stock = stock;
		this.price = price;
		this.ecotax = ecotax;
		this.ean = ean;
		this.description = description;
		this.image_url = image_url;
		this.size = size;
		this.color = color;
		this.weight = weight;
		this.deliveries = deliveries;
	}	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getEcotax() {
		return ecotax;
	}

	public void setEcotax(double ecotax) {
		this.ecotax = ecotax;
	}


	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}	
	
	public int getId() {
		return id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public Set<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Set<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
		// ajouter test du produit sans produitDetail
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", sku=" + sku + ", stock=" + stock + ", price=" + price + ", ecotax="
				+ ecotax + ", ean=" + ean + ", description=" + description + ", image_url=" + image_url + ", size="
				+ size + ", color=" + color + ", weight=" + weight + "]";
	}
 

	}	
	
