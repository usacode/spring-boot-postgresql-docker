package com.teamviewer.ecommerce.model.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Paul Ngouabeu
 * This class holds the model for the OrderItem.
 *
 */
@Data
@EqualsAndHashCode
@Entity
public class OrderItem {
	/**
	 * This property defines an Id for the OrderItem.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	/**
	 * This property defines a Product for the OrderItem.
	 */
	private String productId;
	
	/**
	 * This property defines a total price for the OrderItem.
	 */
	private BigDecimal totalPrice;
	
	/**
	 * This property defines a customerName for the OrderItem.
	 */
	private String customerName;

	/**
	 * This property defines an Order for the OrderItem.
	 */
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

	/**
	 * This property defines a quantity for the OrderItem.
	 */
	private int quantity;
}
