package com.teamviewer.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.teamviewer.ecommerce.model.dto.ProductDTO;
import com.teamviewer.ecommerce.model.entity.Product;
import com.teamviewer.ecommerce.service.ProductService;
import com.teamviewer.ecommerce.validation.FrontValidation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


import java.util.List;



/**
 * 
 * @author Paul Ngouabeu
 * This class holds the API's for the Products.
 *
 */
@RestController
@RequestMapping("/api/products")
@Tag(name="Products",description="Operations related to products.")
public class ProductController {

	/**
     * This is service for the Products
     * @see ProductService.class
     */
    private final ProductService productService;
    
    /**
     * This is front validation for the Products
     * @see FrontValidation.class
     */
    private final FrontValidation frontValidation;
    
    /**
     * ProductController constructor class
     * @param productService - productService
     * @param frontValidation - frontValidation
     */
    @Autowired
    public ProductController(final ProductService productService,final FrontValidation frontValidation) {
    	this.productService=productService;
    	this.frontValidation=frontValidation;
    }

    /**
     * This method handles the request for getting Products list.
     * @return It returns all Products form the database.
     */
    @Operation(summary="Get a list of all products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
    
    

    /**
     * This method handles the request for getting a Product.
     * @param productId
     * @return It returns a Product form the database.
     */
    @Operation(summary="Get a product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") final String id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    /**
     * This method handles the request for creating a Product.
     * @param productDTO
     * @return It returns a created Product form the database.
     */
    @Operation(summary="Create a new product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody final ProductDTO productDTO) {
    	frontValidation.getInvalidProductFields(productDTO);
        final Product  product = productDTO.toEntity();
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }
    
    /**
     * This method handles the request for updating a Product.
     * @param productId
     * @param updatedProductDTO
     * @return It returns a updated Product form the database.
     */
    @Operation(summary="Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") final String id, @Valid @RequestBody final ProductDTO updatedProductDTO) {
    	frontValidation.getInvalidProductFields(updatedProductDTO);
        final Product updatedProduct = updatedProductDTO.toEntity();
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, updatedProduct));
    }

    /**
     * This method handles the request for deleting a Product.
     * @return It returns a status for acknowledging the deletion of a record.
     */
    @Operation(summary="Delete a product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") final String id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
