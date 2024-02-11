package com.teamviewer.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamviewer.ecommerce.model.entity.Product;

/**
 * 
 * @author Paul Ngouabeu
 * This interface holds the persistence layer of the Products
 *
 */
public interface ProductRepository extends JpaRepository<Product, String> {
}
