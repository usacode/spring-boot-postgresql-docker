package com.teamviewer.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamviewer.ecommerce.model.dto.OrderDTO;
import com.teamviewer.ecommerce.model.dto.PostOrderDTO;
import com.teamviewer.ecommerce.model.entity.Orders;
import com.teamviewer.ecommerce.service.OrderService;
import com.teamviewer.ecommerce.validation.FrontValidation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @MockBean
    private FrontValidation frontValidation;

    @Test
    public void getAllOrders_ShouldReturnListOfOrders() throws Exception {
      
        List<Orders> orders = Arrays.asList(order());
        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].orderDate").isNotEmpty())
                .andExpect(jsonPath("$[0].customerName").value("1"));
    }

    @Test
    public void getOrderById_ShouldReturnOrder() throws Exception {

    
        when(orderService.getOrderById("1")).thenReturn(order());

        // Act & Assert
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.orderDate").isNotEmpty())
                .andExpect(jsonPath("$.customerName").value("1"));
    }

    @Test
    public void createOrder_ShouldReturnCreatedOrder() throws Exception {
     
        PostOrderDTO postOrderDTO = new PostOrderDTO();
        postOrderDTO.setOrderId("1");
        postOrderDTO.setOrderItemIds(Arrays.asList("item1", "item2"));

        when(frontValidation.validateCustomerSesssion()).thenReturn("Customer");
        when(orderService.createOrder(any(PostOrderDTO.class)))
                .thenReturn(order());

        
        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postOrderDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.orderDate").isNotEmpty())
                .andExpect(jsonPath("$.customerName").value("1"));
    }

    @Test
    public void updateOrder_ShouldReturnUpdatedOrder() throws Exception {

        OrderDTO updatedOrderDTO = new OrderDTO();
        updatedOrderDTO.setOrderDate(LocalDateTime.now());
        updatedOrderDTO.setCustomerName("Customer 2");

        when(frontValidation.validateCustomerSesssion()).thenReturn("Customer");
        when(orderService.updateOrder("1", updatedOrderDTO))
                .thenReturn(order());

        // Act & Assert
        mockMvc.perform(put("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedOrderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.orderDate").isNotEmpty())
                .andExpect(jsonPath("$.customerName").value("1"));
    }

    @Test
    public void deleteOrder_ShouldReturnNoContent() throws Exception {
        // Arrange
        doNothing().when(orderService).deleteOrder("1");

        // Act & Assert
        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());
    }
    
    private Orders order() {
    	Orders order = new Orders();
    	order.setId("1");
    	order.setCustomerName("1");
    	order.setOrderDate(LocalDateTime.now());
    	return order;
    }
}