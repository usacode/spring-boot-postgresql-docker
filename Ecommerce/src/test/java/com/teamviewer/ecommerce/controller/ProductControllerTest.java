package com.teamviewer.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamviewer.ecommerce.model.dto.ProductDTO;
import com.teamviewer.ecommerce.model.entity.Product;
import com.teamviewer.ecommerce.service.ProductService;
import com.teamviewer.ecommerce.validation.FrontValidation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @MockBean
    private FrontValidation frontValidation;

    @Test
    public void getAllProducts_ShouldReturnListOfProducts() throws Exception {

     
        List<Product> products = Arrays.asList(product());
        when(productService.getAllProducts()).thenReturn(products);

    
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Product_1"))
                .andExpect(jsonPath("$[0].price").value("10.0"));
    }

    @Test
    public void getProductById_ShouldReturnProduct() throws Exception {
       
        when(productService.getProductById("1")).thenReturn(product());

       
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Product_1"))
                .andExpect(jsonPath("$.price").value("10.0"));
    }

    @Test
    public void createProduct_ShouldReturnCreatedProduct() throws Exception {
    	ProductDTO productDTO = new ProductDTO();
        productDTO.setName("New Product");
        productDTO.setPrice(new BigDecimal("20.00"));
        Mockito.doNothing().when(frontValidation).getInvalidProductFields(any());
        when(productService.createProduct(any())).thenReturn(product());

        // Act & Assert
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Product_1"))
                .andExpect(jsonPath("$.price").value("10.0"));
    }

    @Test
    public void updateProduct_ShouldReturnUpdatedProduct() throws Exception {
        // Arrange
    	 ProductDTO updatedProductDTO = new ProductDTO();
    	 
         updatedProductDTO.setName("Updated Product");
         updatedProductDTO.setPrice(new BigDecimal("30.00"));

        Mockito.doNothing().when(frontValidation).getInvalidProductFields(any());
        when(productService.updateProduct("1", product())).thenReturn(product());

        // Act & Assert
        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProductDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProduct_ShouldReturnNoContent() throws Exception {
    	  ProductDTO updatedProductDTO = new ProductDTO();
          updatedProductDTO.setName("Updated Product");
          updatedProductDTO.setPrice(new BigDecimal("30.00"));
       
        doNothing().when(productService).deleteProduct("1");

   
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
    
    private Product product() {
    	 Product product = new Product();
         product.setId("1");
         product.setName("Product_1");
         product.setPrice(new BigDecimal("10.00"));
         return product;
    }
}