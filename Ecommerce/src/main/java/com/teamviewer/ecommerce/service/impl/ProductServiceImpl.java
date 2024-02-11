package com.teamviewer.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.teamviewer.ecommerce.exception.NotFoundException;
import com.teamviewer.ecommerce.model.entity.Product;
import com.teamviewer.ecommerce.repository.ProductRepository;
import com.teamviewer.ecommerce.service.ProductService;

import java.util.List;
/**
 * 
 * @author Paul Ngouabeu
 * This class holds the implementation logic for Products.
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * This is repository for the Products
     * @see ProductRepository.class
     */
    private final ProductRepository productRepository;
    
    /**
     * ProductServiceImpl constructor class
     * @param productRepository - productRepository
     */
    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository){
    	this.productRepository=productRepository;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Product getProductById(final String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found.",HttpStatus.NOT_FOUND,productId));
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Product createProduct(final Product product) {
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Product updateProduct(final String productId, final Product product) {
        if (productRepository.existsById(productId)) {
            product.setId(productId);
            return productRepository.save(product);
        }
        throw new NotFoundException("Product not found.",HttpStatus.NOT_FOUND,productId);
    }

    /**
     * {@inheritDoc}
     * 
     */
	@Override
	public void deleteProduct(final String productId) {
		 if (productRepository.existsById(productId)) {
			 productRepository.deleteById(productId);
	      }else {
	      throw new NotFoundException("Product not found.",HttpStatus.NOT_FOUND,productId);
	      }
	}

 
}