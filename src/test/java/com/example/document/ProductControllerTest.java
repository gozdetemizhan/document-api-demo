package com.example.document;

import com.example.document.controller.ProductController;
import com.example.document.dto.ProductDTO;
import com.example.document.exception.ResourceNotFoundException;
import com.example.document.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("Should return all products")
    void shouldReturnAllProducts() throws Exception {
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO(1L, "Product A", 100),
                new ProductDTO(2L, "Product B", 200)
        );

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(products.size()))
                .andExpect(jsonPath("$[0].name").value("Product A"))
                .andExpect(jsonPath("$[1].price").value(200));
    }

    @Test
    @DisplayName("Should return 404 when product not found")
    void shouldReturnNotFoundForInvalidProduct() throws Exception {
        Long invalidId = 999L;

        when(productService.getProductById(invalidId))
                .thenThrow(new ResourceNotFoundException("Product not found with id: " + invalidId));

        mockMvc.perform(get("/api/products/" + invalidId))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Product not found with id")));
    }

    @Test
    @DisplayName("Should return 400 when product name is blank")
    void shouldReturnBadRequestForInvalidPostData() throws Exception {
        String invalidJson = """
            {
                "name": "",
                "price": 100
            }
            """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Product name must not be blank"));
    }
}
