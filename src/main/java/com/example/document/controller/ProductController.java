package com.example.document.controller;

import com.example.document.dto.ProductDTO;
import com.example.document.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Retrieve all products", description = "Returns a list of all products from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Retrieve a product by ID", description = "Returns a product for the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO dto = productService.getProductById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new product", description = "Creates a new product and returns the saved product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.addProduct(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing product", description = "Updates a product with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product successfully updated"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        ProductDTO updated = productService.updateProduct(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
