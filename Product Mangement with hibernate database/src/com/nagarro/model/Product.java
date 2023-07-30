package com.nagarro.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userNameOrEmail;
	private String title;
	private String quantity;
	private String size;
	String image;
	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}
    public int getId()
    {
    	return id;
    }
	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product(String userNameOrEmail, String title, String quantity, String size, String image) {
		super();
		this.userNameOrEmail = userNameOrEmail;
		this.title = title;
		this.quantity = quantity;
		this.size = size;
		this.image = image;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [userNameOrEmail=" + userNameOrEmail + ", title=" + title + ", quantity=" + quantity + ", size="
				+ size + ", image=" + (image) + "]";
	}

}
