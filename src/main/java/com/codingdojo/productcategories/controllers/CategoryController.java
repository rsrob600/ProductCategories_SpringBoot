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
public class CategoryController {
	private final CategoryService categoryService;
	private final ProductService productService;
	
	public CategoryController(CategoryService categoryService, ProductService productService) {
		this.categoryService = categoryService;
		this.productService = productService;
	}
	
	// display add new category page
	@RequestMapping("/categories/new")
	public String newCategory(@ModelAttribute("category")Category category) {
		return "view/newCategory.jsp";
	}
	
	// save new category page
	@PostMapping("/addCategory")
	public String addCategory(@Valid @ModelAttribute("category")Category category, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "view/newCategory.jsp";
		} else {
			categoryService.addCategory(category);
			return "redirect:/categories/new";
		}
	}
	
	// show category by id
	@RequestMapping("/categories/{id}")
	public String showCategory(Model model, @PathVariable("id")Long id, @ModelAttribute("category") Category category) {
		Category catList = categoryService.findCategory(id);
		model.addAttribute("category", catList);
		model.addAttribute("products", productService.getAllCategoriesUnassignedById(id));
		return "view/showCategories.jsp";
	}
	
	// save product to a category by id
	@PostMapping("/categories/{id}")
	public String addProductCategory(@PathVariable ("id") Long Id, @RequestParam("product") Long prodId ) {
		Category catList = categoryService.findCategory(Id);
		Product prodList = productService.findProduct(prodId);
		List<Product> products = catList.getProducts();
		products.add(prodList);
		categoryService.addCategory(catList);
		return "redirect:/categories/" + Id;
	}
	
	
	
	
}
