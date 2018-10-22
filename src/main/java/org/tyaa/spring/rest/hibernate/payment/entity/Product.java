package org.tyaa.spring.rest.hibernate.payment.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product implements Serializable {

	private static final long serialVersionUID = -3763311450210903492L;
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private BigDecimal price;
	private String image;
	@ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String name, String description, BigDecimal price, String image) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
