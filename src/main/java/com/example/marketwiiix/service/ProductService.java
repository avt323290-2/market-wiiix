package com.example.marketwiiix.service;

import com.example.marketwiiix.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Сервис продуктов.
 */
public interface ProductService {

    /**
     * Получить все продукты.
     *
     * @return Список всех продуктов.
     */
    List<Product> findAll();

    /**
     * Получить продукт по его идентификатору.
     *
     * @param id Идентификатор продукта.
     * @return Продукт, если найден, иначе пустое значение.
     */
    Optional<Product> findById(Integer id);

    /**
     * Поиск продуктов по имени и описанию.
     *
     * @param searchKey Ключ поиска.
     * @return Набор продуктов, удовлетворяющих критериям поиска.
     */
    Set<Product> searchProductsByNameAndDescription(String searchKey);

    /**
     * Получить страницу продуктов с пагинацией.
     *
     * @param pageable Параметры страницы.
     * @return Страница с продуктами.
     */
    Page<Product> findPaginated(Pageable pageable);
}