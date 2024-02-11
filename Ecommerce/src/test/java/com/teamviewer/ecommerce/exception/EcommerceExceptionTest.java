package com.teamviewer.ecommerce.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EcommerceExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Ecommerce Exception Message";
        EcommerceException exception = new EcommerceException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Ecommerce Exception Message";
        Throwable cause = new RuntimeException("Cause Exception");

        EcommerceException exception = new EcommerceException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testSetter() {
        EcommerceException exception = new EcommerceException("", null);

        assertEquals("", exception.getMessage());
    }
}
