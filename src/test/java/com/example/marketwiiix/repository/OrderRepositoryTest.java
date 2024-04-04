package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.Category;
import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.entity.User;
import com.example.marketwiiix.entity.Order; // Предположим, что Order находится в том же пакете, что и OrderRepositoryTest
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository; // Предположим, что OrderRepository уже существует

    @Test
    void testSave() {
        User user = User.builder()
                .userId(1)
                .build();
        Product product = Product.builder()
                .productId(1)
                .name("product name1")
                .price(BigDecimal.valueOf(10.99))
                .category(Category.builder().categoryId(1).build())
                .description("description1")
                .build();
        List<Product> products = new ArrayList<>();
        products.add(product);

        Order expected = Order.builder()
                .orderId(4)
                .price(BigDecimal.valueOf(10.99))
                .user(user)
                .products(products)
                .build();
        Order order = Order.builder()
                .price(BigDecimal.valueOf(10.99))
                .user(user)
                .products(products)
                .build();
        Order actual = orderRepository.saveAndFlush(order);
        assertEquals(actual, expected);
    }

    @Test
    void testFindAllByUser() {
        User user = User.builder().userId(2).build();
        Order order = Order.builder()
                .orderId(2)
                .price(BigDecimal.valueOf(0.2))
                .createdAt(LocalDateTime.of(2023, 6, 14, 9, 37, 59, 613918))
                .user(User.builder().userId(2).build())
                .build();
        List<Order> expected = new ArrayList<>();
        expected.add(order);
        List<Order> actual = orderRepository.findAllByUser(user);
        assertEquals(actual, expected);
    }
}