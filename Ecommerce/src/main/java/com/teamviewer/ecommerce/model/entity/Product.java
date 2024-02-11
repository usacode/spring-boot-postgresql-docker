package com.teamviewer.ecommerce.model.entity;




import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Paul Ngouabeu
 * This class holds the model for the Products.
 *
 */
@Data
@EqualsAndHashCode
@Entity
public class Product {
	/**
	 * This property defines an Id for the Products.
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
    
    /**
	 * This property defines a name for the Products.
	 */
	private String name;
	
	/**
	 * This property defines a price for the Products.
	 */
	private BigDecimal price;
}
