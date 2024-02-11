package com.teamviewer.ecommerce.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.teamviewer.ecommerce.model.entity.OrderItem;

/**
 * 
 * @author Paul Ngouabeu
 * This interface holds the persistence layer of the OrderItem
 *
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}