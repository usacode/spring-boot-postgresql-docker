package com.teamviewer.ecommerce.validation.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;


import com.teamviewer.ecommerce.exception.BadRequestException;
import com.teamviewer.ecommerce.exception.UnauthorizedAccessException;
import com.teamviewer.ecommerce.model.dto.OrderDTO;
import com.teamviewer.ecommerce.model.dto.OrderItemDTO;
import com.teamviewer.ecommerce.model.dto.PostOrderDTO;
import com.teamviewer.ecommerce.model.dto.ProductDTO;
import com.teamviewer.ecommerce.validation.FrontValidation;

import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class FrontValidationImplTest {

    @Mock
    private HttpSession httpSession;

    private FrontValidation frontValidation;

    @BeforeEach
    void setUp() {
        frontValidation = new FrontValidationImpl(httpSession);
    }

    @Test
    void testGetInvalidProductFields() {
        ProductDTO product = new ProductDTO();
        product.setName(null);
        product.setPrice(BigDecimal.valueOf(-1));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> frontValidation.getInvalidProductFields(product));

        assertEquals("Invalid product.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testGetInvalidOrderFields() {
        OrderDTO order = new OrderDTO();
        order.setCustomerName(null);
        order.setOrderDate(LocalDateTime.now());

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> frontValidation.getInvalidOrderFields(order));

        assertEquals("Invalid order.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(Collections.singletonList("The customerName must be a string that is not null."),
                exception.getAllFields());
    }

    @Test
    void testGetInvalidOrderItemFields() {
        OrderItemDTO orderItem = new OrderItemDTO();
        orderItem.setProductId(null);
        orderItem.setQuantity(0);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> frontValidation.getInvalidOrderItemtFields(orderItem));

        assertEquals("Invalid orderItem.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());

    }

    @Test
    void testGetInvalidOrderCreationFields() {
        PostOrderDTO postOrderDTO = new PostOrderDTO();
        postOrderDTO.setOrderId(null);
        postOrderDTO.setOrderItemIds(Collections.emptyList());

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> frontValidation.getInvalidOrderCreationFields(postOrderDTO));

        assertEquals("Invalid orderItem.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(Collections.singletonList("The orderId must be a string that is not null."),
                exception.getAllFields());
    }

    @Test
    void testValidateCustomerSession() {
        when(httpSession.getAttribute("customerName")).thenReturn("John Henri");

        String result = frontValidation.validateCustomerSesssion();

        assertEquals("John Henri", result);
    }

    @Test
    void testValidateCustomerSessionUnauthorized() {
        when(httpSession.getAttribute("customerName")).thenReturn(null);

        UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class,
                () -> frontValidation.validateCustomerSesssion());

        assertEquals("You are unauthorized. Your session is expired.", exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        assertEquals("customerName", exception.getFieldName());
    }
}