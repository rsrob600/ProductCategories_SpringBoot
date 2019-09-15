package com.codingdojo.productcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.productcategories.models.Product;
import com.codingdojo.productcategories.models.Category;
import com.codingdojo.productcategories.repositories.CategoryRepository;
import com.codingdojo.productcategories.repositories.ProductRepository;

@Service
public class ProductService {
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	
	public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}
	
	// get all products
	public List<Product> getAllProducts(){
		return (List<Product>) productRepository.findAll();
	}
	
	// add a product
	public void addProduct(Product p) {
		productRepository.save(p);
	}
	
	// get one product by id
	public Product findProduct(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}
	}
	
	// get all available categories not assigned to product
	public List<Product> getAllCategoriesUnassignedById(Long id){
		List<Product> prodList = this.getAllProducts();
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isPresent()) {
			List<Product> catSansProd = optionalCategory.get().getProducts();
			prodList.removeAll(catSansProd);
			return prodList;
		} else {
			return null;
		}
	}
	
}
