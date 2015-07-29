package fr.m2i.stage.marketplace.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cat4ID")
	private int id;
	
	@Column(name="cat4")
	private String name;
	
	@Column(name="visible")
	private boolean isVisible;
	
	public Category() {}	
	
	public Category(String name, boolean isVisible) {
		this.name = name;
		this.isVisible = isVisible;
	}
	
	public int getId() {
		return id;
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
}
