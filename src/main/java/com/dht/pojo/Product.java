package com.dht.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToMany
	@JoinTable(name = "prod_manufacturer", 
			joinColumns = { @JoinColumn(name = "product_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "manufacturer_id") }
	)
	private Set<Manufacturer> manufacturers;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(Set<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

}
