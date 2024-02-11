package com.teamviewer.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.teamviewer.ecommerce.model.dto.OrderItemDTO;
import com.teamviewer.ecommerce.model.entity.OrderItem;
import com.teamviewer.ecommerce.service.OrderItemService;
import com.teamviewer.ecommerce.validation.FrontValidation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;


import java.util.List;



/**
 * 
 * @author Paul Ngouabeu
 * This class holds the API's for the OrderItem.
 *
 */
@RestController
@RequestMapping("/api/order-items")
@Tag(name="OrderItems",description="Operations related to order items.")
public class OrderItemController {

	/**
     * This is service for the OrderItem
     * @see OrderItemService.class
     */
    private final OrderItemService orderItemService;
    
    /**
     * This is front validation for the Products
     * @see FrontValidation.class
     */
    private final FrontValidation frontValidation;
    
    /**
     * OrderItemController constructor class
     * @param orderItemService - orderItemService
     * @param frontValidation - frontValidation
     */
    @Autowired
    public OrderItemController(final OrderItemService orderItemService,final FrontValidation frontValidation) {
    	this.orderItemService=orderItemService;
    	this.frontValidation=frontValidation;
    }

    /**
     * This method handles the request for getting OrderItem list.
     * @return It returns all OrderItem form the database.
     */
    @Operation(summary="Get a list of all order items")
    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.getAllOrderItems());
    }
    
    

    /**
     * This method handles the request for getting a OrderItem.
     * @param id -id
     * @return It returns a OrderItem form the database.
     */
    @Operation(summary="Get an order item by ID")
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable("id") final String id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.getOrderItemById(id));
    }
    

    /**
     * This method handles the request for creating a OrderItem.
     * @param orderItemDTO - orderItemDTO
     * @return It returns a created OrderItem form the database.
     */
    @Operation(summary="Create a new order item")
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@Valid @RequestBody final OrderItemDTO orderItemDTO) {
    	String customerName = frontValidation.validateCustomerSesssion();
    	frontValidation.getInvalidOrderItemtFields(orderItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.createOrderItem(orderItemDTO,customerName));
    }
    

    /**
     * This method handles the request for updating a OrderItem.
     * @param id - id
     * @param orderItemDTO - orderItemDTO
     * @return It returns a updated OrderItem form the database.
     */
    @Operation(summary="Update an existing order item")
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable("id") final String id, @Valid @RequestBody final OrderItemDTO orderItemDTO) {
    	frontValidation.validateCustomerSesssion();
    	frontValidation.getInvalidOrderItemtFields(orderItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.updateOrderItem(id, orderItemDTO));
    }

    /**
     * This method handles the request for deleting a OrderItem.
     * @param id -id
     * @return It returns a status for acknowledging the deletion of a record.
     */
    @Operation(summary="Delete an order item by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable("id") final String id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
