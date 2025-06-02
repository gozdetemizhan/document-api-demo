package com.example.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Product Object")
public class ProductDTO {

    @Schema(description = "Unique ID of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Product name must not be blank")
    @Schema(description = "Name of the product", example = "Wireless Mouse", required = true)
    private String name;

    @Min(value = 0, message = "Price must be zero or positive")
    @Schema(description = "Price of the product", example = "299", required = true)
    private int price;

    public ProductDTO() {}

    public ProductDTO(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
