package fr.m2i.stage.marketplace.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Merchant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String address;
	private String password;
	private String zipcode;
	private String country;
	private String login;	
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	private String SIRET;
	private Catalog catalog;
	
	public Merchant() {}
	
	public Merchant(int id, String name, String address, String password, String zipcode, String country, String login,
			String firstname, String lastname, String email, String phoneNumber, String sIRET, Catalog catalog) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.password = password;
		this.zipcode = zipcode;
		this.country = country;
		this.login = login;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		SIRET = sIRET;
		this.catalog = catalog;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSIRET() {
		return SIRET;
	}
	public void setSIRET(String sIRET) {
		SIRET = sIRET;
	}
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
}
