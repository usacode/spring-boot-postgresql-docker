package com.teamviewer.ecommerce.model.dto;



import java.math.BigDecimal;

import com.teamviewer.ecommerce.model.entity.Product;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Paul Ngouabeu
 * This class holds the dto for the Products.
 *
 */
@Data
@EqualsAndHashCode
public class ProductDTO {
	
	
	/**
	 * This property defines a name for the ProductDTO.
	 */
    private String name;

    /**
	 * This property defines a price for the ProductDTO.
	 */
    private BigDecimal price;
    
   
    /**
     * This method helps build the Product model
     * @return It returns a Product
     */
    public Product toEntity() {
        final Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        return product;
    }
}