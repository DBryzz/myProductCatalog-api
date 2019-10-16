package com.bryzz.productcatalogapi.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bryzz.productcatalogapi.entity.Category;
import com.bryzz.productcatalogapi.entity.Product;
import com.bryzz.productcatalogapi.exceptions.ResourceNotFoundException;
import com.bryzz.productcatalogapi.repo.CategoryRepo;
import com.bryzz.productcatalogapi.repo.ProductRepo;

@Service
public class ProductDaoService {

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	Category category;
	
	public List<Product> getProducts() {
		return productRepo.findAll();
	}
	
	public Resource<Product> getProduct(int id) {
		Optional<Product> product = productRepo.findById(id);
		
		if(!product.isPresent()) {
			throw new ResourceNotFoundException("id - " +id);
		}
		
		Resource<Product> resource = new Resource<Product>(product.get());
		//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getCategories());
		//resource.add(linkTo.withRel("all-categories"));
		
		return resource;
	}
	
	public ResponseEntity<Object> removeProduct(int id) {
		Optional<Product> delProduct = productRepo.findById(id);
		productRepo.deleteById(id);
		return new ResponseEntity<>(delProduct, HttpStatus.NO_CONTENT);
	}
	
	
	public ResponseEntity<Object> postProduct(int id, Product product) { //@Valid
		Optional<Category> category_1 = categoryRepo.findById(id);
		if(!category_1.isPresent()) {
			throw new ResourceNotFoundException("id - " +id);
		}
		 
		product.putCategory(category_1.get());
		Product newProduct = productRepo.save(product);
		
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newProduct.getPxtId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	public ResponseEntity<Object> editProduct(int pxtId, int catId, Product newProduct) {
		
		Optional<Product> product = productRepo.findById(pxtId);
		if(product.isPresent()) {
			
			Optional<Category> category_1 = categoryRepo.findById(catId);
			if(!category_1.isPresent()) {
				throw new ResourceNotFoundException("category_id - " +catId);
			}
			
	//		newProduct.putCategory(category.get());
			
			
			Product productUpdate = product.get();
			productUpdate.setPxtName(newProduct.getPxtName());
			productUpdate.setQuantity(newProduct.getQuantity());
			productUpdate.setPrice(newProduct.getPrice());
			Category newCat = new Category(category_1.get().getCatId(), category_1.get().getCatName());
			newCat.setCatId(product.get().getCatId());
			newCat.setCatName(product.get().getCatName());
			productUpdate.putCategory(newCat);
			
			return new ResponseEntity<>(productRepo.save(productUpdate), HttpStatus.NO_CONTENT);
			
		}
		throw new ResourceNotFoundException("product_id - " +pxtId);
		
		
		 
		
		
	}
	
	
	
}
