package com.teamviewer.ecommerce.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class NotFoundExceptionTest {

    @Test
    public void testConstructorAndGetters() {
        String message = "Not Found Message";
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        Object fieldName = new Object();

        NotFoundException exception = new NotFoundException(message, statusCode, fieldName);

        assertEquals(message, exception.getMessage());
        assertEquals(statusCode, exception.getStatusCode());
        assertEquals(fieldName, exception.getFieldName());
    }

    @Test
    public void testSetters() {
        NotFoundException exception = new NotFoundException("", null, null);

        String newMessage = "New Not Found Message";
        HttpStatus newStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        Object newFieldName = new Object();

   
        exception.setStatusCode(newStatusCode);
        exception.setFieldName(newFieldName);

       
        assertEquals(newStatusCode, exception.getStatusCode());
        assertEquals(newFieldName, exception.getFieldName());
    }
}