package com.bryzz.productcatalogapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bryzz.productcatalogapi.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
