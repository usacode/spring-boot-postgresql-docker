package com.teamviewer.ecommerce.model.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Paul Ngouabeu
 * This class holds the dto for the Order Creation.
 *
 */
@Data
@EqualsAndHashCode
public class PostOrderDTO {

	/**
	 * This property defines a orderId for the Order.
	 */
	private String orderId;
	
	/**
	 * This property defines a list of orderItemIds for the Order.
	 */
	private List<String> orderItemIds;

}
