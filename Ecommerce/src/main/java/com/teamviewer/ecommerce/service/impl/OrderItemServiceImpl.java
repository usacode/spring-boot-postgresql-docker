package com.teamviewer.ecommerce.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamviewer.ecommerce.exception.NotFoundException;
import com.teamviewer.ecommerce.model.dto.OrderItemDTO;
import com.teamviewer.ecommerce.model.entity.Orders;
import com.teamviewer.ecommerce.model.entity.OrderItem;
import com.teamviewer.ecommerce.model.entity.Product;
import com.teamviewer.ecommerce.repository.OrderItemRepository;
import com.teamviewer.ecommerce.repository.OrderRepository;
import com.teamviewer.ecommerce.service.OrderItemService;
import com.teamviewer.ecommerce.service.ProductService;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



/**
 * 
 * @author Paul Ngouabeu
 * This class holds the implementation logic for OrderItem.
 *
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    
	/**
     * This is repository for the OrderItem
     * @see OrderItemRepository.class
     */
    private final OrderItemRepository orderItemRepository;
    

	/**
     * This is repository for the Order
     * @see OrderRepository.class
     */
    private final OrderRepository orderRepository;
     
  
    
    /**
     * This is service for the Orders
     * @see ProductServiceImpl.class
     */
    private final ProductService productService;
    
    
   
    /**
     * OrderItemServiceImpl constructor class
     * @param orderItemRepository - orderItemRepository
     * @param productService - productService
     * @param orderRepository - orderRepository
     */
    @Autowired
    public OrderItemServiceImpl(final OrderItemRepository orderItemRepository,final ProductService productService,final OrderRepository orderRepository) {
    	this.orderItemRepository=orderItemRepository;
    	this.productService=productService;
    	this.orderRepository=orderRepository;
    	
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public OrderItem getOrderItemById(final String orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow(() -> new NotFoundException("Order item not found.",HttpStatus.NOT_FOUND,orderItemId));
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Transactional
    @Override
    public OrderItem createOrderItem(final OrderItemDTO  orderItemDTO,final String customerSession) {
    	 final Product product = productService.getProductById(orderItemDTO.getProductId());
    	 Optional<Product> optionalPrt = Optional.ofNullable(product);
    	 if(optionalPrt.isPresent()) {
    		 //Create Order
    		 Orders orders = new Orders();
    		 orders.setOrderDate(LocalDateTime.now());
    		 orders.setCustomerName(customerSession);
    	// Save the Orders instance
    	orderRepository.save(orders);
    	
    	//Calculates totalPrice
    	 BigDecimal totalPrice= calculateAndSetTotalPrice(orderItemDTO);
    	//Create OrderItem
    	 OrderItem orderItem = new OrderItem();
         orderItem.setProductId(orderItemDTO.getProductId());
         orderItem.setQuantity(orderItemDTO.getQuantity());
         orderItem.setTotalPrice(totalPrice);
         orderItem.setCustomerName(customerSession);
         orderItem.setOrder(orders);
      // Save the OrderItem instance
         return orderItemRepository.save(orderItem);
    	 }
    	 throw new NotFoundException("Product not found.",HttpStatus.NOT_FOUND,orderItemDTO.getProductId());
        
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public OrderItem updateOrderItem(final String orderItemId, final OrderItemDTO orderItemDTO) {
    	
    	OrderItem existingOrderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new NotFoundException("OrderItem not found.",HttpStatus.NOT_FOUND,orderItemId));

        // Update fields based on the request
        existingOrderItem.setProductId(orderItemDTO.getProductId());
        existingOrderItem.setQuantity(orderItemDTO.getQuantity());
        existingOrderItem.setTotalPrice(calculateAndSetTotalPrice(orderItemDTO));

        // Save and return the updated order item
        return orderItemRepository.save(existingOrderItem);

    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void deleteOrderItem(final String orderItemId) {
        if (orderItemRepository.existsById(orderItemId)) {
        	orderItemRepository.deleteById(orderItemId);
        }else {
        throw new NotFoundException("Order item not found.",HttpStatus.NOT_FOUND,orderItemId);
        }
    }
    
    /**
     * This helper method calculate and set total price for OrderItem.
     * @param orderItem - orderItem
     * @return It is returns total price by product quantity.
     */
    private BigDecimal calculateAndSetTotalPrice(OrderItemDTO orderItem) {
        // Calculate total price based on quantity and product price
    	BigDecimal totalPrice =null;
        if (orderItem.getQuantity() != null && orderItem.getProductId() != null) {
            // Fetch product price from database
        	final Product product = productService.getProductById(orderItem.getProductId());
            final BigDecimal productPrice =product.getPrice();

            // Calculate total price
           totalPrice = productPrice.multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        }
		return totalPrice;
    }
}
