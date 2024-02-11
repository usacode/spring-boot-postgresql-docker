package com.teamviewer.ecommerce.validation;



import com.teamviewer.ecommerce.model.dto.OrderDTO;
import com.teamviewer.ecommerce.model.dto.OrderItemDTO;
import com.teamviewer.ecommerce.model.dto.PostOrderDTO;
import com.teamviewer.ecommerce.model.dto.ProductDTO;

/**
 * 
 * @author Paul Ngouabeu
 * This interface declares methods for the FrontValidationImpl class.
 *
 */
public interface FrontValidation {

	/**
	 * This method validates whether a product inputs meet the requirements.
	 * @param ProductDTO - product
	 */
	void getInvalidProductFields(ProductDTO product);

	/**
	 * This method validates whether an order inputs meet the requirements.
	 * @param OrderDTO - order
	 */
	void getInvalidOrderFields(OrderDTO order);

	/**
	 * This method validates whether an orderItem inputs meet the requirements.
	 * @param orderItem - orderItem
	 */
	void getInvalidOrderItemtFields(OrderItemDTO orderItem);
	
	/**
	 * This method validates whether a customer session still valid.
	 * @return It returns a customer session
	 */
	String validateCustomerSesssion();
	
	/**
	 * This method validates whether an order inputs meet the requirements.
	 * @param postOrderDTO - postOrderDTO
	 */
	void getInvalidOrderCreationFields(PostOrderDTO postOrderDTO);
	
	

}