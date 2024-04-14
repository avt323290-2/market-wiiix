package com.example.marketwiiix.service.impl;

import com.example.marketwiiix.dto.CartDto;
import com.example.marketwiiix.entity.Cart;
import com.example.marketwiiix.entity.Order;
import com.example.marketwiiix.entity.User;
import com.example.marketwiiix.entity.converter.CartConverter;
import com.example.marketwiiix.repository.OrderRepository;
import com.example.marketwiiix.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Реализация сервиса заказов.
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartConverter cartConverter;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CartConverter cartConverter) {
        this.orderRepository = orderRepository;
        this.cartConverter = cartConverter;
    }

    /**
     * Сохранить заказ на основе корзины и идентификатора пользователя.
     *
     * @param cartDto Корзина с продуктами.
     * @param userId  Идентификатор пользователя.
     * @return Сохраненный заказ.
     */
    @Override
    public Order save(CartDto cartDto, Integer userId) {
        Cart cart = cartConverter.convertToEntity(cartDto);
        User user = User.builder()
                .userId(userId)
                .build();
        Order order = Order.builder()
                .price(BigDecimal.valueOf(cart.getTotalPrice()))
                .user(user)
                .products(cart.getProducts())
                .build();
        return orderRepository.saveAndFlush(order);
    }

    /**
     * Получить список заказов пользователя по его идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return Список заказов пользователя.
     */
    @Override
    public List<Order> readOrders(Integer userId) {
        User user = User.builder()
                .userId(userId)
                .build();
        return orderRepository.findAllByUser(user);
    }

    /**
     * Получить заказ по его идентификатору.
     *
     * @param orderId Идентификатор заказа.
     * @return Заказ.
     */
    @Override
    public Order read(Integer orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}