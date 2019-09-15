package com.codingdojo.productcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.productcategories.models.Category;
import com.codingdojo.productcategories.models.Product;
import com.codingdojo.productcategories.repositories.CategoryRepository;
import com.codingdojo.productcategories.repositories.ProductRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	
	public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository=productRepository;
	}
	
	// get all categories
	public List<Category> getAllCategories(){
		return (List<Category>) categoryRepository.findAll();
	}
	
	// add a category
	public void addCategory(Category c) {
		categoryRepository.save(c);
	}
	
	// get one category by id
	public Category findCategory(Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isPresent()) {
			return optionalCategory.get();
		} else {
			return null;
		}
	}
	
	// get all available products not assigned category
	public List<Category> getAllProductsUnassignedById(Long id){
		List<Category> catList = this.getAllCategories();
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			List<Category> prodSansCat = optionalProduct.get().getCategories();
			catList.removeAll(prodSansCat);
			return catList;
		} else {
			return null;
		}
	}
	
}
