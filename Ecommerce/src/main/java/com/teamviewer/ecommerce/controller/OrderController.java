package com.teamviewer.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.teamviewer.ecommerce.model.dto.OrderDTO;
import com.teamviewer.ecommerce.model.dto.PostOrderDTO;
import com.teamviewer.ecommerce.model.entity.Orders;
import com.teamviewer.ecommerce.service.OrderService;

import com.teamviewer.ecommerce.validation.FrontValidation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


import java.util.List;



/**
 * 
 * @author Paul Ngouabeu
 * This class holds the API's for the Orders.
 *
 */
@RestController
@RequestMapping("/api/orders")
@Tag(name="Orders",description="Operations related to orders.")
public class OrderController {

	/**
     * This is service for the Orders.
     * @see OrderService.class
     */
    private final OrderService orderService;
    
    /**
     * This is front validation for the Products
     * @see FrontValidation.class
     */
    private final FrontValidation frontValidation;
    
    
    
    

    
    /**
     * OrderController constructor class
     * @param orderService - orderService
     * @param frontValidation - frontValidation
     * @param sessionData - sessionData
     */
    @Autowired
    public OrderController(final OrderService orderService,final FrontValidation frontValidation) {
    	this.orderService=orderService;
    	this.frontValidation=frontValidation;
    	
    	
    }

    /**
     * This method handles the request for getting Orders list.
     * @return It returns all Orders form the database.
     */
    @Operation(summary="Get a list of all orders")
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        return 	ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }
    
    

    /**
     * This method handles the request for getting a Order.
     * @param id - id
     * @return It returns a Order form the database.
     */
    @Operation(summary="Get an order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") final String id) {
        return 	ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
    }

    
    /**
     * This method handles the request for creating a Order.
     * @param postOrderDTO - postOrderDTO
     * @return It returns a created Order form the database.
     */
    @Operation(summary="Create a new order")
    @PostMapping
    public ResponseEntity<Orders> createOrder(@Valid @RequestBody final PostOrderDTO postOrderDTO) {
    	frontValidation.validateCustomerSesssion();
    	frontValidation.getInvalidOrderCreationFields(postOrderDTO);
    	Orders order = orderService.createOrder(postOrderDTO);

        return 	ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    
    
    /**
     * This method handles the request for updating a Order.
     * @param orderId
     * @param updatedOrderDTO
     * @return It returns a updated Order form the database.
     */
    @Operation(summary="Update an existing order")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") final String orderId, @Valid @RequestBody final OrderDTO updatedOrderDTO) {
    	frontValidation.validateCustomerSesssion();
    	frontValidation.getInvalidOrderFields(updatedOrderDTO);
        return 	ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrder(orderId, updatedOrderDTO));
    }

    
    /**
     * This method handles the request for deleting a Order.
     * @param id - id
     * @return It returns a status for acknowledging the deletion of a record.
     */
    @Operation(summary="Delete an order by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") final String id) {
    	frontValidation.validateCustomerSesssion();
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
 
}
