package com.example.productosToti.dto;

public record ProductResponse(Long id, String name, Double price, int stock, Long categoryId, String categoryName) {
}
