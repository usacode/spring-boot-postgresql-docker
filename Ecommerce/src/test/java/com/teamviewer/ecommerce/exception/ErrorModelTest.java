package com.teamviewer.ecommerce.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ErrorModelTest {

    @Test
    public void testConstructorAndGetters() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Error Message";
        Object errors = new Object();

        ErrorModel errorModel = new ErrorModel(status, message, errors);

        assertEquals(status, errorModel.getStatus());
        assertEquals(message, errorModel.getMessage());
        assertEquals(errors, errorModel.getErrors());
    }

    @Test
    public void testSetters() {
        ErrorModel errorModel = new ErrorModel(HttpStatus.BAD_REQUEST, "", null);

        HttpStatus newStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String newMessage = "New Error Message";
        Object newErrors = new Object();

        errorModel.setStatus(newStatus);
        errorModel.setMessage(newMessage);
        errorModel.setErrors(newErrors);

        assertEquals(newStatus, errorModel.getStatus());
        assertEquals(newMessage, errorModel.getMessage());
        assertEquals(newErrors, errorModel.getErrors());
    }
}