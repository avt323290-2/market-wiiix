package com.example.marketwiiix.service;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.entity.Order;

import java.util.List;

/**
 * Сервис заказов.
 */
public interface OrderService {

    /**
     * Сохранить заказ на основе информации о корзине и идентификаторе пользователя.
     *
     * @param cartDto Информация о корзине.
     * @param userId  Идентификатор пользователя.
     * @return Сохраненный заказ.
     */
    Order save(CartDto cartDto, Integer userId);

    /**
     * Получить все заказы пользователя по его идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return Список заказов пользователя.
     */
    List<Order> readOrders(Integer userId);

    /**
     * Получить заказ по его идентификатору.
     *
     * @param orderId Идентификатор заказа.
     * @return Заказ.
     */
    Order read (Integer orderId);
}