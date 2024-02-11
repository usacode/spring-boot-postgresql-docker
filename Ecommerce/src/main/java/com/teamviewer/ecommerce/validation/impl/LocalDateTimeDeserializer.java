package com.teamviewer.ecommerce.validation.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.teamviewer.ecommerce.exception.BadRequestException;


/**
 * 
 * @author Paul Ngouabeu
 * This class holds the LocalDateTime JsonDeserializer logic for localDate input.
 *
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    /**
     * This method deserializes the LocalDateTime to meet the json Jackson format.
     * @param p - JsonParser
     * @param ctxt - DeserializationContext
     * @return It returns a deserialized LocalDateTime
     */
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateAsString = p.getText();
        try {
            return LocalDateTime.parse(dateAsString, FORMATTER);
        } catch (Exception e) {
          
        	throw new BadRequestException("Invalid order.",HttpStatus.BAD_REQUEST,p.currentName()+" is in an invalid format.");
        }
    }
}