package com.teamviewer.ecommerce.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;


import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.teamviewer.ecommerce.exception.NotFoundException;
import com.teamviewer.ecommerce.model.dto.OrderItemDTO;
import com.teamviewer.ecommerce.model.entity.OrderItem;
import com.teamviewer.ecommerce.model.entity.Orders;
import com.teamviewer.ecommerce.model.entity.Product;
import com.teamviewer.ecommerce.repository.OrderItemRepository;
import com.teamviewer.ecommerce.repository.OrderRepository;
import com.teamviewer.ecommerce.service.ProductService;







@ExtendWith(MockitoExtension.class)
class OrderItemServiceImplTest {

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
    	orderItemService = new OrderItemServiceImpl(orderItemRepository,productService,orderRepository);
    }
    @Test
    void createOrderItem_ValidProduct_ShouldReturnOrderItem() {
   
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId("validProductId");
        orderItemDTO.setQuantity(2);

        Product product = new Product();
        product.setPrice(BigDecimal.TEN);

        Orders order = new Orders();

        when(productService.getProductById("validProductId")).thenReturn(product);
        when(orderRepository.save(any(Orders.class))).thenReturn(order);
        when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> {
            OrderItem savedOrderItem = invocation.getArgument(0);
            savedOrderItem.setId("generatedId");
            return savedOrderItem;
        });

      
        OrderItem result = orderItemService.createOrderItem(orderItemDTO, "customerSession");

      
        assertNotNull(result);
        assertEquals("generatedId", result.getId());
        assertEquals("customerSession", result.getCustomerName());
     
    }

    @Test
    void createOrderItem_InvalidProduct_ShouldThrowNotFoundException() {
       // Arrange
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId("invalidProductId");

        when(productService.getProductById("invalidProductId")).thenThrow(new NotFoundException("Product not found.", HttpStatus.NOT_FOUND, "invalidProductId"));

    
        assertThrows(NotFoundException.class, () -> orderItemService.createOrderItem(orderItemDTO, "customerSession"));
    }

    @Test
    void updateOrderItem_ValidOrderItemId_ShouldReturnUpdatedOrderItem() {
 
        String orderItemId = "validOrderItemId";
        OrderItemDTO updatedOrderItemDTO = new OrderItemDTO();
        updatedOrderItemDTO.setProductId("validProductId");
        updatedOrderItemDTO.setQuantity(3);

        OrderItem existingOrderItem = new OrderItem();
        existingOrderItem.setId(orderItemId);
        existingOrderItem.setProductId("originalProductId");
        existingOrderItem.setQuantity(2);
        existingOrderItem.setTotalPrice(BigDecimal.TEN);

        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(5)); // Set a valid price

        when(orderItemRepository.findById(orderItemId)).thenReturn(Optional.of(existingOrderItem));
        when(productService.getProductById("validProductId")).thenReturn(product); // Return the mocked product
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(existingOrderItem);

       // Act
        OrderItem result = orderItemService.updateOrderItem(orderItemId, updatedOrderItemDTO);

     
        assertNotNull(result);
        assertEquals(orderItemId, result.getId());
        assertEquals("validProductId", result.getProductId());
        assertEquals(3, result.getQuantity());
        
   
    }

    @Test
    void updateOrderItem_InvalidOrderItemId_ShouldThrowNotFoundException() {

        String invalidOrderItemId = "invalidOrderItemId";

        when(orderItemRepository.findById(invalidOrderItemId)).thenReturn(Optional.empty());

     
        assertThrows(NotFoundException.class, () -> orderItemService.updateOrderItem(invalidOrderItemId, new OrderItemDTO()));
    }
}