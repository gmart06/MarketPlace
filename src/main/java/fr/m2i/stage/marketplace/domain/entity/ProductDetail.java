package fr.m2i.stage.marketplace.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String sku;
	int stock;
	double price;
	double ecotax;
	int ean;
	String description;
	String image_url;
	String size;
	String color;
	int weight;
	
	public ProductDetail() {}
	
	public ProductDetail(int id, String sku, int stock, double price, double ecotax, int ean, String description, String image_url,
			String size, String color, int weight) {
		super();
		this.id = id;
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

	public int getEan() {
		return ean;
	}	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEan(int ean) {
		this.ean = ean;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
