package com.teamviewer.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamviewer.ecommerce.model.dto.OrderItemDTO;
import com.teamviewer.ecommerce.model.entity.OrderItem;
import com.teamviewer.ecommerce.service.OrderItemService;
import com.teamviewer.ecommerce.validation.FrontValidation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderItemController.class)
@AutoConfigureMockMvc
public class OrderItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderItemService orderItemService;

    @MockBean
    private FrontValidation frontValidation;

    @Test
    public void getAllOrderItems_ShouldReturnListOfOrderItems() throws Exception {
        
        
        List<OrderItem> orderItems = Arrays.asList(orderItem());
        when(orderItemService.getAllOrderItems()).thenReturn(orderItems);

        mockMvc.perform(get("/api/order-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].productId").value("Product_1"))
                .andExpect(jsonPath("$[0].quantity").value(5));
    }

    @Test
    public void getOrderItemById_ShouldReturnOrderItem() throws Exception {
       
        when(orderItemService.getOrderItemById("1")).thenReturn(orderItem());

      
        mockMvc.perform(get("/api/order-items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.productId").value("Product_1"))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    public void createOrderItem_ShouldReturnCreatedOrderItem() throws Exception {
        // Arrange
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId("1");
        orderItemDTO.setQuantity(5);

        when(frontValidation.validateCustomerSesssion()).thenReturn("Customer");
        when(orderItemService.createOrderItem(any(OrderItemDTO.class), any(String.class)))
                .thenReturn(orderItem());

        // Act & Assert
        mockMvc.perform(post("/api/order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderItemDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.productId").value("Product_1"))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    public void updateOrderItem_ShouldReturnUpdatedOrderItem() throws Exception {
        // Arrange
        OrderItemDTO updatedOrderItemDTO = new OrderItemDTO();
        updatedOrderItemDTO.setProductId("1");
        updatedOrderItemDTO.setQuantity(10);

        when(frontValidation.validateCustomerSesssion()).thenReturn("Customer");
        when(orderItemService.updateOrderItem("1", updatedOrderItemDTO))
                .thenReturn(orderItem());

        // Act & Assert
        mockMvc.perform(put("/api/order-items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedOrderItemDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.productId").value("Product_1"))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    public void deleteOrderItem_ShouldReturnNoContent() throws Exception {

        doNothing().when(orderItemService).deleteOrderItem("1");


        mockMvc.perform(delete("/api/order-items/1"))
                .andExpect(status().isNoContent());
    }
    
    private OrderItem orderItem() {
    	OrderItem orderItem = new OrderItem();
    	orderItem.setId("1");
    	orderItem.setProductId("Product_1");
    	orderItem.setQuantity(5);
    	return orderItem;
    	
    }
}