package com.teamviewer.ecommerce.model.dto;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.teamviewer.ecommerce.validation.impl.LocalDateTimeDeserializer;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Paul Ngouabeu
 * This class holds the dto for the Orders.
 *
 */
@Data
@EqualsAndHashCode
public class OrderDTO {

    /**
	 * This property defines the DateTime for the order.
	 */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderDate;
    
    /**
	 * This property defines the customerName for the order.
	 */
    private String customerName;
    
 
}