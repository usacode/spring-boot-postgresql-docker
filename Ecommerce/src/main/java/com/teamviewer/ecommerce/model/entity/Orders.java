package com.teamviewer.ecommerce.model.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author Paul Ngouabeu
 * This class holds the model for the Orders.
 *
 */
@Data
@EqualsAndHashCode
@Entity
public class Orders {
	/**
	 * This property defines an Id for the Orders.
	 */
    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	
	/**
	 * This property defines a list of orderItems for the Orders.
	 */
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    /**
	 * This property defines the DateTime for creation for the Orders.
	 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime orderDate;
    
    /**
	 * This property defines the customerName for the Orders.
	 */
    private String customerName;

	/**
	 * This servers as helper to populate the OrderItem.
	 * @param orderItem - orderItem
	 */
    public void addOrderItem(final OrderItem orderItem) {
        if (orderItem != null) {
            orderItem.setOrder(this);
            this.orderItems.add(orderItem);
        }
    }

}
