package com.teamviewer.ecommerce.service;

import java.util.List;

import com.teamviewer.ecommerce.model.dto.OrderItemDTO;
import com.teamviewer.ecommerce.model.entity.OrderItem;


/**
 * 
 * @author Paul Ngouabeu
 * This interface declares methods for the OrderItemServiceImpl class
 *
 */
public interface OrderItemService {
	/**
	 * This method gets all the OrderTems from the DB.
	 * @return It returns a list of OrderItems.
	 */
    List<OrderItem> getAllOrderItems();
    
    /**
   	 * This method gets a OrderItem based on orderId.
   	 * @param orderItemId
   	 * @return It returns an orderItemId.
   	 */
    OrderItem getOrderItemById(String orderItemId);
    
    /**
   	 * This method creates OrderItem into the database. 
   	 * @param orderItemDTO - orderItemDTO
   	 * @param customerName -customerName
   	 * @return It returns an OrderItem.
   	 */
    OrderItem createOrderItem(OrderItemDTO  orderItemDTO,String customerSessionName);
    
    /**
     * This method updates an OrderItem based on Id from database.
     * @param orderItemId - orderItemId
     * @param orderItemDTO - orderItemDTO
     * @return It returns an OrderItem.
     */
    OrderItem updateOrderItem(String orderItemId, OrderItemDTO orderItemDTO);
    
    /**
     * This method deletes an OrderItem based on orderItemId from database.
     * @param orderId
     */
    void deleteOrderItem(String orderItemId);
}