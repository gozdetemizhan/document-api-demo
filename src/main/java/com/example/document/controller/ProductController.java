package com.example.document.controller;

import com.example.document.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    @Operation(
        summary = "Tüm ürünleri getir",
        description = "Sabit veri kaynağından tüm ürünleri döner."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ürünler başarıyla getirildi"),
        @ApiResponse(responseCode = "500", description = "Sunucu hatası")
    })
    public List<Product> getAllProducts() {
        return List.of(
            new Product(1L, "Laptop", 15000),
            new Product(2L, "Mouse", 350),
            new Product(3L, "Keyboard", 800)
        );
    }
}
