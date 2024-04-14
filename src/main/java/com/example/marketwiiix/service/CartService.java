package com.example.marketwiiix.service;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.dto.ProductDto;

import java.util.List;

/**
 * Сервис корзины.
 */
public interface CartService {

    /**
     * Добавить продукт в корзину.
     *
     * @param productId Идентификатор продукта.
     * @param cartDto   DTO корзины.
     * @return DTO корзины после добавления продукта.
     */
    CartDto addProduct(Integer productId, CartDto cartDto);

    /**
     * Удалить продукт из корзины.
     *
     * @param productId Идентификатор продукта.
     * @param cartDto   DTO корзины.
     * @return DTO корзины после удаления продукта.
     */
    CartDto removeProduct(Integer productId, CartDto cartDto);

    /**
     * Очистить корзину.
     *
     * @param cartDto DTO корзины.
     * @return Пустая DTO корзины.
     */
    CartDto clear(CartDto cartDto);

    /**
     * Рассчитать общую стоимость продуктов в корзине.
     *
     * @param productsDto Список DTO продуктов.
     * @return Общая стоимость продуктов.
     */
    double calculateTotalPrice(List<ProductDto> productsDto);
}