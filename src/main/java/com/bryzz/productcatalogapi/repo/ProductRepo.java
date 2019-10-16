package com.bryzz.productcatalogapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bryzz.productcatalogapi.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
