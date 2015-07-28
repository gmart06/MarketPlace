package fr.m2i.stage.marketplace.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private boolean isVisible;
	
	@OneToMany(mappedBy=("???"))
	private List<Product> listProduct;
	
	public Category() {}	
	
	public Category(String name, boolean isVisible) {
		this.name = name;
		this.isVisible = isVisible;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public List<Product> getListProduct() {
		return listProduct;
	}
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
}
