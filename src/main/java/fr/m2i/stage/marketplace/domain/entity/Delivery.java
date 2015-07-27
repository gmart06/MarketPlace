package fr.m2i.stage.marketplace.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Delivery {	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String name;
	Date delay;
	double fees;
	
	public Delivery() {}

	public Delivery(int id, String name, Date delay, double fees) {
		super();
		this.id = id;
		this.name = name;
		this.delay = delay;
		this.fees = fees;
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

	public Date getDelay() {
		return delay;
	}

	public void setDelay(Date delay) {
		this.delay = delay;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}
}
