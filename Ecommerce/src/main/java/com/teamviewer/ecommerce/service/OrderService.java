package com.teamviewer.ecommerce.service;

import java.util.List;

import com.teamviewer.ecommerce.model.dto.OrderDTO;
import com.teamviewer.ecommerce.model.dto.PostOrderDTO;
import com.teamviewer.ecommerce.model.entity.Orders;


/**
 * 
 * @author Paul Ngouabeu
 * This interface declares methods for the OrderServiceImpl class
 *
 */
public interface OrderService {
	/**
	 * This method gets all the Orders from the DB.
	 * @return It returns a list of Orders.
	 */
    List<Orders> getAllOrders();
    
    /**
	 * This method gets a Order based on orderId.
	 * @param orderId
	 * @return It returns an Order.
	 */
    Orders getOrderById(String orderId);
    
    /**
   	 * This method creates Order into the database. 
   	 * @param postOrderDTO - postOrderDTO 
   	 * @return It returns a Order.
   	 */
    Orders createOrder(PostOrderDTO postOrderDTO);
    
    /**
     * This method updates an Order based on productId from database.
     * @param orderId
     * @param order
     * @return It returns an Order.
     */
    Orders updateOrder(String orderId, OrderDTO order);
    
    /**
     * This method deletes an Order based on productId from database.
     * @param orderId
     */
    void deleteOrder(String orderId);

	

	

	
}