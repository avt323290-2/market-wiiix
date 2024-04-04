package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.Order;
import com.example.marketwiiix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий для работы с заказами.
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * Находит все заказы для указанного пользователя.
     *
     * @param user пользователь
     * @return список заказов для указанного пользователя
     */
    List<Order> findAllByUser(User user);

    /**
     * Находит заказ по его идентификатору.
     *
     * @param id идентификатор заказа
     * @return заказ с указанным идентификатором, если он существует, иначе null
     */
    Order findByOrderId(Integer id);

}