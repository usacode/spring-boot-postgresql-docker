package com.teamviewer.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;


/**
 * This class creates a session for the customer
 * @author Paul Ngouabeu
 *
 */
@RestController
@Tag(name="Session",description="Customer session.")
public class SessionController {

	/**
	 * This helps set a session
	 */
    @Autowired
    private HttpSession httpSession;

    /**
     * This method handles the request to set a session
     * @param customerName - customerName
     * @return It returns a notification message
     */
    @Operation(summary="Set your session")
    @PostMapping("/set-customer-name")
    public ResponseEntity<String> setCustomerName(@RequestParam("customerName") String customerName) {
        // Set customerName in the session
        httpSession.setAttribute("customerName", customerName);

        return ResponseEntity.status(HttpStatus.OK).body("Customer name set in session: " + customerName);
    }
}