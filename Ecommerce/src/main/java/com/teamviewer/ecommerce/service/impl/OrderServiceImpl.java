package com.teamviewer.ecommerce.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamviewer.ecommerce.exception.NotFoundException;


import com.teamviewer.ecommerce.model.entity.Orders;
import com.teamviewer.ecommerce.model.dto.OrderDTO;
import com.teamviewer.ecommerce.model.dto.PostOrderDTO;
import com.teamviewer.ecommerce.model.entity.OrderItem;

import com.teamviewer.ecommerce.repository.OrderItemRepository;
import com.teamviewer.ecommerce.repository.OrderRepository;

import com.teamviewer.ecommerce.service.OrderService;



import java.util.List;


/**
 * 
 * @author Paul Ngouabeu
 * This class holds the implementation logic for Orders.
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	/**
     * This is the repository for the Orders
     * @see OrderRepository.class
     */
    private final OrderRepository orderRepository;
    
  
    
	/**
     * This is the repository for the OrderItem
     * @see OrderItemRepository.class
     */
    private final OrderItemRepository orderItemRepository;
    
    
 
    /**
     * OrderServiceImpl constructor class
     * @param orderRepository - orderRepository
     * @param orderItemRepository - orderItemRepository
     */
    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository,final OrderItemRepository orderItemRepository) {
    	this.orderRepository=orderRepository;
    	this.orderItemRepository= orderItemRepository;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Orders getOrderById(final String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found.",HttpStatus.NOT_FOUND,orderId));
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Transactional
    @Override
    public Orders createOrder(final PostOrderDTO postOrderDTO) {
        Orders orders = orderRepository.findById(postOrderDTO.getOrderId())
                .orElseThrow(() -> new NotFoundException("Orders not found.",HttpStatus.NOT_FOUND, postOrderDTO.getOrderId()));

        List<OrderItem> orderItems = orderItemRepository.findAllById(postOrderDTO.getOrderItemIds());
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(orders);
            orderItemRepository.save(orderItem);
        }
        return orders;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Orders updateOrder(final String orderId, final OrderDTO order) {
    	Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found.",HttpStatus.NOT_FOUND,orderId));

        // Update fields based on the request
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setCustomerName(order.getCustomerName());

        // Save and return the updated order
        return orderRepository.save(existingOrder);
       
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void deleteOrder(final String orderId) {
        if (orderRepository.existsById(orderId)) {
        	orderRepository.deleteById(orderId);
        }else {
        throw new NotFoundException("Order not found.",HttpStatus.NOT_FOUND,orderId);
        }
    }
 
 
}