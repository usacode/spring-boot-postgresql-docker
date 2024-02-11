package com.teamviewer.ecommerce.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.teamviewer.ecommerce.exception.NotFoundException;
import com.teamviewer.ecommerce.model.entity.Product;
import com.teamviewer.ecommerce.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }
    
    private static final String ID ="123456";

    @Test
    public void testGetAllProducts() {
        List<Product> mockProducts = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAllProducts();

        assertEquals(mockProducts, result);
    }

    @Test
    public void testGetProductById() {
        String productId = ID;
        Product mockProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Product result = productService.getProductById(productId);

        assertEquals(mockProduct, result);
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        String productId = ID;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.getProductById(productId));
    }

    @Test
    public void testCreateProduct() {
        Product mockProduct = new Product();
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);

        Product result = productService.createProduct(mockProduct);

        assertEquals(mockProduct, result);
    }

    @Test
    public void testUpdateProduct() {
        String productId = ID;
        Product mockProduct = new Product();
        when(productRepository.existsById(productId)).thenReturn(true);
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);

        Product result = productService.updateProduct(productId, mockProduct);

        assertEquals(mockProduct, result);
    }

    @Test
    public void testUpdateProduct_ProductNotFound() {
        String productId = ID;
        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> productService.updateProduct(productId, new Product()));
    }

    @Test
    public void testDeleteProduct() {
        String productId = ID;
        when(productRepository.existsById(productId)).thenReturn(true);

        assertDoesNotThrow(() -> productService.deleteProduct(productId));
    }

    @Test
    public void testDeleteProduct_ProductNotFound() {
        String productId = ID;
        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> productService.deleteProduct(productId));
    }
}
