package com.bryzz.productcatalogapi.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Product {

	@Id
	@GeneratedValue
	private int pxtId;
	private String pxtName;
	private int quantity;
	private long price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;
	
	
	// Constuctors
	@Autowired
	public Product() {
		
	}
	
	@Autowired
	public Product(int pxtId, String pxtName, int quantity, long price) {
		super();
		this.pxtId = pxtId;
		this.pxtName = pxtName;
		this.quantity = quantity;
		this.price = price;
		}
	
	// Getters and Setters
	public int getPxtId() {
		return pxtId;
	}
	public void setPxtId(int pxtId) {
		this.pxtId = pxtId;
	}
	public String getPxtName() {
		return pxtName;
	}
	public void setPxtName(String pxtName) {
		this.pxtName = pxtName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	
	
	public Category getCategory() {
		return new Category(getCatId(), getCatName());
	}
	
	@JsonIgnore
	public String getCatName() {
		return category.getCatName();
	}
	
	@JsonIgnore
	public int getCatId() {
		return category.getCatId();
	}
	
	
	@JsonIgnore
	public void setCategory() {
		this.category = new Category(getCatId(), getCatName());
		//return this;
	}
	
	@JsonIgnore
	public void putCategory(Category category) {
		this.category = category;
		//return this;
	}
	
	
	
/*	@JsonIgnore
	public void setCategory(Category category) {
		this.category = category;
		//return this;
	} */

	
	

	

	@Override
	public String toString() {
		return String.format("Product [pxtId=%s, pxtName=%s, quantity=%s, price=%s]", pxtId, pxtName,
				quantity, price);
	}
	
	
	
}
