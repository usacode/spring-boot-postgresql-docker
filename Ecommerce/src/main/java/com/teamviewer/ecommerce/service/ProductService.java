package com.teamviewer.ecommerce.service;

import java.util.List;

import com.teamviewer.ecommerce.model.entity.Product;

/**
 * 
 * @author Paul Ngouabeu
 * This interface declares methods for the ProductServiceImpl class.
 *
 */
public interface ProductService {
	/**
	 * This method gets all the Products from the database.
	 * @return It returns a list of Products.
	 */
    List<Product> getAllProducts();
    
    /**
	 * This method gets a Product based on productId.
	 * @return It returns a Product.
	 */
    Product getProductById(String productId);
    
    /**
	 * This method creates Product into the database. 
	 * @return It returns a Product.
	 */
    Product createProduct(Product product);
    
    /**
     * This method updates a Product based on productId from database.
     * @param productId
     * @param product
     * @return It returns a Product.
     */
    Product updateProduct(String productId, Product product);
    
   /**
    * This method deletes a Product based on productId from database.
    * @param productId
    */
    void deleteProduct(String productId);
}
