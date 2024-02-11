package com.teamviewer.ecommerce.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;


@ExtendWith(MockitoExtension.class)
public class UnauthorizedAccessExceptionTest {

    @Test
    public void testConstructorAndGetters() {
        String message = "Unauthorized Access Message";
        HttpStatus statusCode = HttpStatus.UNAUTHORIZED;
        Object fieldName = new Object();

        UnauthorizedAccessException exception = new UnauthorizedAccessException(message, statusCode, fieldName);

        assertEquals(message, exception.getMessage());
        assertEquals(statusCode, exception.getStatusCode());
        assertEquals(fieldName, exception.getFieldName());
    }

    @Test
    public void testSetters() {
        UnauthorizedAccessException exception = new UnauthorizedAccessException("", null, null);

        String newMessage = "New Unauthorized Access Message";
        HttpStatus newStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        Object newFieldName = new Object();

        
        exception.setStatusCode(newStatusCode);
        exception.setFieldName(newFieldName);

     
        assertEquals(newStatusCode, exception.getStatusCode());
        assertEquals(newFieldName, exception.getFieldName());
    }
}