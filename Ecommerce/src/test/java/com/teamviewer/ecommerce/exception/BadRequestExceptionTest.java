package com.teamviewer.ecommerce.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class BadRequestExceptionTest {

    @Test
    public void testConstructorAndGetters() {
        String message = "Bad Request Message";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        Object allFields = new Object();

        BadRequestException exception = new BadRequestException(message, statusCode, allFields);

        assertEquals(message, exception.getMessage());
        assertEquals(statusCode, exception.getStatusCode());
        assertEquals(allFields, exception.getAllFields());
    }

    @Test
    public void testSetters() {
        BadRequestException exception = new BadRequestException("", HttpStatus.BAD_REQUEST, new Object());

       
        HttpStatus newStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        Object newAllFields = new Object();

       
        exception.setStatusCode(newStatusCode);
        exception.setAllFields(newAllFields);

     
        assertEquals(newStatusCode, exception.getStatusCode());
        assertEquals(newAllFields, exception.getAllFields());
    }
}