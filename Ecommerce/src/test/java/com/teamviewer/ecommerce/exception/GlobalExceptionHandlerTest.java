package com.teamviewer.ecommerce.exception;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @Test
    public void testHandleNotFoundException() {
        NotFoundException ex = new NotFoundException("Not Found", HttpStatus.NOT_FOUND, "fieldName");

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<Object> responseEntity = handler.handleNotFoundException(ex);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        ErrorModel errorModel = (ErrorModel) responseEntity.getBody();
        assertNotNull(errorModel);
        assertEquals("Not Found", errorModel.getMessage());
        assertEquals("fieldName", errorModel.getErrors());
    }

    @Test
    public void testHandleBadRequestException() {
        BadRequestException ex = new BadRequestException("Bad Request", HttpStatus.BAD_REQUEST, new Object());

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<Object> responseEntity = handler.handleBadRequestException(ex);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorModel errorModel = (ErrorModel) responseEntity.getBody();
        assertNotNull(errorModel);
        assertEquals("Bad Request", errorModel.getMessage());
        
    }

    @Test
    public void testHandleUnauthorizedAccessException() {
        UnauthorizedAccessException ex = new UnauthorizedAccessException("Unauthorized", HttpStatus.UNAUTHORIZED, "fieldName");

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<Object> responseEntity = handler.handleUnauthorizedAccessException(ex);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());

        ErrorModel errorModel = (ErrorModel) responseEntity.getBody();
        assertNotNull(errorModel);
        assertEquals("Unauthorized", errorModel.getMessage());
        assertEquals("fieldName", errorModel.getErrors());
    }
}
