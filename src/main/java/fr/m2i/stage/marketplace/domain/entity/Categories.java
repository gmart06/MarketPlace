package fr.m2i.stage.marketplace.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categories {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private long id;
	
	private String name;
	
	@Column(name="level_hierarchy")
	private long hierarchy;
	
	@Column(name="parent_id")
	private long parent;
	
	
	@Column(name="visible")
	private boolean isVisible;
	
	public Categories() {}	
	
	public Categories(String name, boolean isVisible) {
		this.name = name;
		this.isVisible = isVisible;
	}

	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLevel_hierarchy() {
		return hierarchy;
	}

	public void setLevel_hierarchy(long level_hierarchy) {
		this.hierarchy = level_hierarchy;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent_id) {
		this.parent = parent_id;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}



}
