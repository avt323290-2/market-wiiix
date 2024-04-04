package com.example.marketwiiix.service.impl;

import com.example.marketwiiix.entity.Category;
import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.entity.User;
import com.example.marketwiiix.repository.CategoryRepository;
import com.example.marketwiiix.repository.ProductRepository;
import com.example.marketwiiix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса категорий.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    /**
     * Получить все категории.
     *
     * @return Список категорий.
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Получить все продукты с пагинацией.
     *
     * @param paging Параметры пагинации.
     * @return Страница с продуктами.
     */
    @Override
    public Page<Product> findAll(Pageable paging) {
        return productRepository.findAll(paging);
    }

    /**
     * Сохранить категорию.
     *
     * @param category Категория для сохранения.
     * @return Сохраненная категория.
     */
    @Override
    public User save(Category category) {
        return null;
    }

    /**
     * Найти продукты по идентификатору категории с пагинацией.
     *
     * @param categoryId Идентификатор категории.
     * @param pageable   Параметры пагинации.
     * @return Страница с продуктами.
     */
    @Override
    public Page findProductsByCategoryId(Integer categoryId, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> products = productRepository.findProductsByCategoryId(categoryId);
        List<Product> list;
        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), products.size());
    }

}