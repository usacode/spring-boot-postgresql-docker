package com.teamviewer.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamviewer.ecommerce.model.entity.Orders;


/**
 * 
 * @author Paul Ngouabeu
 * This interface holds the persistence layer of the Orders
 *
 */
public interface OrderRepository extends JpaRepository<Orders, String> {
}