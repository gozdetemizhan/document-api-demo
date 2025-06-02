package com.example.document.service;

import com.example.document.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO addProduct(ProductDTO dto);

    ProductDTO updateProduct(Long id, ProductDTO dto);

    void deleteProduct(Long id);
}