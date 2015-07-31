package fr.m2i.stage.marketplace.domain.entity;

import java.util.List;

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
	private long id;
	
	@Column(name="cat4")
	private String name;
	
	@Column(name="cat3")
	private String name3;
	
	@Column(name="cat2")
	private String name2;
	
	@Column(name="cat1")
	private String name1;
	
	@Column(name="cat3ID")
	private int id3;
	
	@Column(name="cat2ID")
	private int id2;
	
	@Column(name="cat1ID")
	private int id1;
	
	@Column(name="visible")
	private boolean isVisible;
	
	public Category() {}	
	
	public Category(String name, boolean isVisible) {
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
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName1() {
		return String.valueOf(name1);
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public int getId3() {
		return id3;
	}

	public void setId3(int id3) {
		this.id3 = id3;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public int getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", name3=" + name3 + ", name2=" + name2 + ", name1=" + name1
				+ ", id3=" + id3 + ", id2=" + id2 + ", id1=" + id1 + ", isVisible=" + isVisible + "]";
	}
}
