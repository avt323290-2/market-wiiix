package com.example.marketwiiix.service;

import com.example.marketwiiix.entity.Category;
import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис категорий.
 */
public interface CategoryService {

    /**
     * Найти все категории.
     *
     * @return Список всех категорий.
     */
    List<Category> findAll();

    /**
     * Найти все продукты с пагинацией.
     *
     * @param paging Параметры страницы.
     * @return Страница продуктов.
     */
    Page<Product> findAll(Pageable paging);

    /**
     * Сохранить категорию.
     *
     * @param category Категория для сохранения.
     * @return Пользователь, если сохранен успешно.
     */
    User save(Category category);

    /**
     * Найти продукты по идентификатору категории с пагинацией.
     *
     * @param categoryId Идентификатор категории.
     * @param pageable   Параметры страницы.
     * @return Страница продуктов по категории.
     */
    Page findProductsByCategoryId(Integer categoryId, Pageable pageable);

}