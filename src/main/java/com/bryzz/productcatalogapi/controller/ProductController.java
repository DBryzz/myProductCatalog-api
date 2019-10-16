package com.bryzz.productcatalogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bryzz.productcatalogapi.entity.Category;
import com.bryzz.productcatalogapi.entity.Product;
import com.bryzz.productcatalogapi.repo.CategoryRepo;
import com.bryzz.productcatalogapi.repo.ProductRepo;
import com.bryzz.productcatalogapi.service.CategoryDaoService;
import com.bryzz.productcatalogapi.service.ProductDaoService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductDaoService productService;
	
	@Autowired
	ProductRepo productRepo; 
	
	
	@GetMapping("/products")
	public List<Product> retrieveProducts() {
		return productService.getProducts();
	}
	
	@GetMapping("/products/{productId}")
	public Resource<Product> retrieveProduct(@PathVariable int productId) {
	/*	Optional<Category> category = categoryRepo.findById(categoryId);
		
		if(!category.isPresent()) {
			throw new CategoryNotFoundException("id - " +categoryId);
		}
		
		Resource<Category> resource = new Resource<Category>(category.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(categoryService.getClass()).getCategories());
		resource.add(linkTo.withRel("all-categories"));
		
		return resource; */
		return productService.getProduct(productId);
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable int productId) {
		return productService.removeProduct(productId);
	}
	
	@PostMapping("/products/category/{categoryId}")
	public ResponseEntity<Object> createCategory(@PathVariable int categoryId, @RequestBody Product product) {
		return productService.postProduct(categoryId, product);
	}
	
	@PutMapping("/products/{productId}/category/{categoryId}")
	public ResponseEntity<Object> updateCategory(@PathVariable int productId, @PathVariable int categoryId, @RequestBody Product product) {
		return productService.editProduct(productId, categoryId, product);
	}

}
