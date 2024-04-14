package com.example.marketwiiix.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO для продуктов.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    /** Идентификатор продукта. */
    @Positive
    private Integer productId;

    /** Название продукта. */
    @Size(min = 2, max = 20)
    private String name;

    /** Описание продукта. */
    @Size(min = 3, max = 500)
    private String description;

    /** Цена продукта. */
    @DecimalMin(value = "0.0")
    private double price;
}