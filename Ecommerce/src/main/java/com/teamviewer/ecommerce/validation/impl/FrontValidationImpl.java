package com.teamviewer.ecommerce.validation.impl;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.teamviewer.ecommerce.exception.BadRequestException;
import com.teamviewer.ecommerce.exception.UnauthorizedAccessException;
import com.teamviewer.ecommerce.model.dto.OrderDTO;
import com.teamviewer.ecommerce.model.dto.OrderItemDTO;
import com.teamviewer.ecommerce.model.dto.PostOrderDTO;
import com.teamviewer.ecommerce.model.dto.ProductDTO;
import com.teamviewer.ecommerce.validation.FrontValidation;

import jakarta.servlet.http.HttpSession;

/**
 * 
 * @author Paul Ngouabeu
 * This class holds the validation logic for user inputs.
 *
 */
@Service
public class FrontValidationImpl implements FrontValidation {

   
   
	/**
     * This is the HttpSession for the session
     * @see OrderRepository.class
     */
    private final HttpSession httpSession;
    
   
    
    /**
     * FrontValidationImpl constructor class
     * @param httpSession - httpSession
     */
    @Autowired
    public FrontValidationImpl(final HttpSession httpSession){
    	this.httpSession=httpSession;
    }
    
    /**
     * invalidFields - It holds the list of messages that are invalid. 
     */
    private final List<String> invalidFields = new ArrayList<>();
    
	
    /**
     * {@inheritDoc}
     * 
     */
	@Override
	public void getInvalidProductFields(final ProductDTO product) {
		invalidFields.clear();
        Optional.ofNullable(product)
        .ifPresent(p -> {
            if (StringUtils.isAnyBlank(p.getName())) {
                invalidFields.add("The name must be a string that is not null.");
            }
            if (p.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                invalidFields.add("The price must be greater than zero.");
            }
        });

        if(!invalidFields.isEmpty()) {
    		throw new BadRequestException("Invalid product.",HttpStatus.BAD_REQUEST,invalidFields);
		}
    }
    
	 /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void getInvalidOrderFields(final OrderDTO order) {
    	invalidFields.clear();
        Optional.ofNullable(order)
                .ifPresent(o -> {
                
                		  if (StringUtils.isAnyBlank(order.getCustomerName())) {
                              invalidFields.add("The customerName must be a string that is not null.");
                          }
                		   if (o.getOrderDate() == null) {
                               invalidFields.add("The orderDate must not be null.");
                           }
                       });

        if(!invalidFields.isEmpty()) {
    		throw new BadRequestException("Invalid order.",HttpStatus.BAD_REQUEST,invalidFields);
		}
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void getInvalidOrderItemtFields(final OrderItemDTO orderItem) {
    	invalidFields.clear();

        Optional.ofNullable(orderItem)
        .ifPresent(i -> {
            if (StringUtils.isAnyBlank(i.getProductId())) {
                invalidFields.add("The productId must be a string that is not null.");
            }
            if (i.getQuantity() <= 0) {
                invalidFields.add("The quantity must be greater than zero.");
            }
        });

        if(!invalidFields.isEmpty()) {
    		throw new BadRequestException("Invalid orderItem.",HttpStatus.BAD_REQUEST,invalidFields);
		}
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void getInvalidOrderCreationFields(final PostOrderDTO postOrderDTO) {
    	invalidFields.clear();

        Optional.ofNullable(postOrderDTO)
        .ifPresent(i -> {
            if (StringUtils.isAnyBlank(i.getOrderId())) {
                invalidFields.add("The orderId must be a string that is not null.");
            }
            
            Optional.ofNullable(i.getOrderItemIds())
            .ifPresentOrElse(
                    ignored -> {},
                    () -> invalidFields.add("The orderItemIds must not be either null or empty."));
        });

        if(!invalidFields.isEmpty()) {
    		throw new BadRequestException("Invalid orderItem.",HttpStatus.BAD_REQUEST,invalidFields);
		}
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
	public String validateCustomerSesssion() {
     final String customerName = (String) httpSession.getAttribute("customerName");
	 if(StringUtils.isAnyBlank(customerName)) {
		 throw new UnauthorizedAccessException("You are unauthorized. Your session is expired.",HttpStatus.UNAUTHORIZED,"customerName");
	 }
	 return customerName;
	}
 
}
