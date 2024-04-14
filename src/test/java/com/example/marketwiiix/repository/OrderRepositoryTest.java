package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.Category;
import com.example.marketwiiix.entity.Order;
import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест для проверки функциональности OrderRepository.
 */
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Проверка сохранения заказа.
     */
    @Test
    void testSave() {
        User user = User.builder()
                .userId(1)
                .build();
        Product product = Product.builder()
                .productId(1)
                .name("product name1")
                .price(10.99)
                .category(Category.builder().categoryId(1).build())
                .description("description1")
                .build();
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        Order expected = Order.builder()
                .orderId(4)
                .price(BigDecimal.valueOf(200.99))
                .user(user)
                .products(products)
                .build();
        Order order = Order.builder()
                .price(BigDecimal.valueOf(200.99))
                .user(user)
                .products(products)
                .build();
        Order actual = orderRepository.saveAndFlush(order);
        assertEquals(actual, expected);
    }
    
    
    /**
     * Проверка поиска всех заказов по пользователю.
     */
    @Test
    void testFindAllByUser() {
        User user = User.builder().userId(2).build();
        Order order = Order.builder()
                .orderId(2)
                .price(new BigDecimal("0.2"))
                .createdAt(LocalDateTime.of(2023, 6, 14, 9, 37, 59, 613918))
                .user(User.builder().userId(2).build())
                .build();
        List<Order> expected = new ArrayList<>();
        expected.add(order);
        List<Order> actual = orderRepository.findAllByUser(user);
        assertEquals(actual, expected);
    }
}