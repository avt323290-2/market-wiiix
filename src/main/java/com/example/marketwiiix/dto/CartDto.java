package com.example.marketwiiix.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO для корзины товаров.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    /** Список продуктов в корзине. */
    @NotNull
    private List<ProductDto> products = new ArrayList<>();

    /** Общая стоимость товаров в корзине. */
    @DecimalMin(value = "0.0")
    private double totalPrice;

    /** Количество товаров в корзине. */
    private int quantity;
}