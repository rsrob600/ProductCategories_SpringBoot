package com.codingdojo.productcategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.productcategories.models.Category;
import com.codingdojo.productcategories.models.Product;
import com.codingdojo.productcategories.services.CategoryService;
import com.codingdojo.productcategories.services.ProductService;

@Controller
public class ProductController {
	private final ProductService productService;
	private final CategoryService categoryService;
	
	public ProductController(ProductService productService, CategoryService categoryService) {	
		this.productService = productService;
		this.categoryService = categoryService;
	}
	
	// display add new product page
	@RequestMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "view/newProduct.jsp";
	}
	
	// save new product page
	@PostMapping("/addProduct")
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "view/newProduct.jsp";
		} else {
			productService.addProduct(product);
			return "redirect:/products/new";
		}
	}
	
	// show product by id
	@RequestMapping("/products/{id}")
	public String showProduct(Model model, @PathVariable("id")Long id, @ModelAttribute("product") Product product) {
		Product prodList = productService.findProduct(id);
		model.addAttribute("product", prodList);
		model.addAttribute("categories", categoryService.getAllProductsUnassignedById(id));
		return "view/showProducts.jsp";
	}
	
	// save category to a product by id
	@PostMapping("/products/{id}")
	public String addCategoryProduct(@PathVariable ("id") Long Id, @RequestParam("category") Long catId ) {
		Product prodList = productService.findProduct(Id);
		Category catList = categoryService.findCategory(catId);
		List<Category> categories = prodList.getCategories();
		categories.add(catList);
		productService.addProduct(prodList);
		return "redirect:/products/" + Id;
	}
	
	
	
	
}
