package com.teamviewer.ecommerce.validation.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.teamviewer.ecommerce.exception.BadRequestException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@ExtendWith(MockitoExtension.class)
class LocalDateTimeDeserializerTest {

    @InjectMocks
    private LocalDateTimeDeserializer deserializer;

    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
  

    @Test
    void deserialize_ValidDateTime_ShouldReturnLocalDateTime() throws IOException {
        
        String validDateTime = "2024-01-10T21:25:47.402Z";
        when(jsonParser.getText()).thenReturn(validDateTime);

        
        LocalDateTime result = deserializer.deserialize(jsonParser, deserializationContext);

        
        verify(jsonParser, times(1)).getText();
        verifyNoMoreInteractions(jsonParser);

        LocalDateTime expectedDateTime = LocalDateTime.parse(validDateTime, FORMATTER);
        assertEquals(expectedDateTime, result);
    }

    @Test
    void deserialize_InvalidDateTime_ShouldThrowBadRequestException() throws IOException {
        
        String invalidDateTime = "2024-01-100T21:25:47"; // Invalid format
        when(jsonParser.getText()).thenReturn(invalidDateTime);

        
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> deserializer.deserialize(jsonParser, deserializationContext));

        assertEquals("Invalid order.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
       
    }
}