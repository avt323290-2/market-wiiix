package com.example.marketwiiix.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

/**
 * DTO для изображений товаров.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto implements Serializable {

    /** Идентификатор продукта, к которому относится изображение. */
    private Integer productId;

    /** Ссылка на изображение. */
    @NotBlank(message = "Ссылка обязательна")
    private String link;
}