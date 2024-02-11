package com.teamviewer.ecommerce.model.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Paul Ngouabeu
 * This class holds the dto for the OrderItem.
 *
 */
@Data
@EqualsAndHashCode
public class OrderItemDTO {
	
	/**
	 * This property defines a productId for the OrderItem.
	 */
    private String productId;
    
  
	/**
	 * This property defines a quantity for the OrderItem.
	 */
    private Integer quantity;


}