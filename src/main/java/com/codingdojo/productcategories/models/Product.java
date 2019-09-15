package com.codingdojo.productcategories.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="products")
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(columnDefinition = "VARCHAR(65)")
	@NotBlank(message = "Please enter a valid product name")
    @Size(min = 2, message = "Product name must be at least 2 characters in length")
    private String name;
	
    private String description;
    private float price;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "categories_products", 
        joinColumns = @JoinColumn(name = "product_id"), 
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    
    public Product() {}

    public Product(String name, String description, float price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public float getPrice() {return price;}
	public void setPrice(float price) {this.price = price;}

	public Date getCreatedAt() {return createdAt;}
	public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

	public Date getUpdatedAt() {return updatedAt;}
	public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

	public List<Category> getCategories() {return categories;}
	public void setCategories(List<Category> categories) {this.categories = categories;}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
