package com.teamviewer.ecommerce.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teamviewer.ecommerce.exception.NotFoundException;
import com.teamviewer.ecommerce.model.dto.OrderDTO;
import com.teamviewer.ecommerce.model.dto.PostOrderDTO;
import com.teamviewer.ecommerce.model.entity.Orders;
import com.teamviewer.ecommerce.repository.OrderItemRepository;
import com.teamviewer.ecommerce.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderServiceImpl orderService;
    
    private static final String ID ="123456";

    @BeforeEach
    public void setUp() {
        orderService = new OrderServiceImpl(orderRepository, orderItemRepository);
    }

    @Test
    public void testGetAllOrders() {
        List<Orders> mockOrders = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Orders> result = orderService.getAllOrders();

        assertEquals(mockOrders, result);
    }

    @Test
    public void testGetOrderById() {
        String orderId = ID;
        Orders mockOrder = new Orders();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        Orders result = orderService.getOrderById(orderId);

        assertEquals(mockOrder, result);
    }

    @Test
    public void testGetOrderById_OrderNotFound() {
        String orderId = ID;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.getOrderById(orderId));
    }

    @Test
    public void testCreateOrder() {
        PostOrderDTO postOrderDTO = new PostOrderDTO();
        postOrderDTO.setOrderId(ID);
        List<String> orderItemIds = new ArrayList<>();
        orderItemIds.add("item1");
        orderItemIds.add("item2");
        postOrderDTO.setOrderItemIds(orderItemIds);

        Orders mockOrders = new Orders();
        when(orderRepository.findById(postOrderDTO.getOrderId())).thenReturn(Optional.of(mockOrders));
        when(orderItemRepository.findAllById(postOrderDTO.getOrderItemIds())).thenReturn(new ArrayList<>());

        Orders result = orderService.createOrder(postOrderDTO);

        assertEquals(mockOrders, result);
    }

    @Test
    public void testCreateOrder_OrderNotFound() {
        PostOrderDTO postOrderDTO = new PostOrderDTO();
        postOrderDTO.setOrderId(ID);

        when(orderRepository.findById(postOrderDTO.getOrderId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.createOrder(postOrderDTO));
    }

    @Test
    public void testUpdateOrder() {
        String orderId = ID;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(LocalDateTime.now());
        orderDTO.setCustomerName("John Henri");

        Orders existingOrder = new Orders();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(existingOrder);

        Orders result = orderService.updateOrder(orderId, orderDTO);

        assertEquals(existingOrder, result);
       
        assertEquals("John Henri", existingOrder.getCustomerName());
    }

    @Test
    public void testUpdateOrder_OrderNotFound() {
        String orderId = ID;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.updateOrder(orderId, new OrderDTO()));
    }

    @Test
    public void testDeleteOrder() {
        String orderId = ID;
        when(orderRepository.existsById(orderId)).thenReturn(true);

        assertDoesNotThrow(() -> orderService.deleteOrder(orderId));
    }

    @Test
    public void testDeleteOrder_OrderNotFound() {
        String orderId = ID;
        when(orderRepository.existsById(orderId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> orderService.deleteOrder(orderId));
    }
}