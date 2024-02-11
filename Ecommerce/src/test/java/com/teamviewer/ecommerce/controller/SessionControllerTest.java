package com.teamviewer.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
@AutoConfigureMockMvc
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void setCustomerName_ShouldSetSessionAttribute() throws Exception {
       
        String customerName = "John Henri";

        
        mockMvc.perform(post("/set-customer-name")
                .param("customerName", customerName))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer name set in session: " + customerName));
    }

    @Test
    public void setCustomerName_ShouldSetSessionAttributeWithMockHttpSession() throws Exception {
    
        String customerName = "John Henri";
        MockHttpSession mockHttpSession = new MockHttpSession();

       
        mockMvc.perform(post("/set-customer-name")
                .param("customerName", customerName)
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer name set in session: " + customerName));

        
        String storedCustomerName = Objects.requireNonNull(mockHttpSession.getAttribute("customerName")).toString();
        assert storedCustomerName.equals(customerName);
    }

    @Test
    public void setCustomerName_ShouldReturnBadRequestIfCustomerNameNotProvided() throws Exception {
      
        mockMvc.perform(post("/set-customer-name"))
                .andExpect(status().isBadRequest());
    }
}