package com.example.marketwiiix.service.impl;

import com.example.marketwiiix.entity.Product;
import com.example.marketwiiix.repository.ProductRepository;
import com.example.marketwiiix.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Реализация сервиса продуктов.
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Получить все продукты.
     *
     * @return Список всех продуктов.
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Найти продукт по его идентификатору.
     *
     * @param id Идентификатор продукта.
     * @return Найденный продукт, если существует.
     */
    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    /**
     * Поиск продуктов по имени и описанию.
     *
     * @param searchKey Ключ поиска.
     * @return Набор найденных продуктов.
     */
    @Override
    public Set<Product> searchProductsByNameAndDescription(String searchKey) {
        return productRepository.findProductsByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchKey, searchKey);
    }

    /**
     * Получить страницу продуктов.
     *
     * @param pageable Параметры страницы.
     * @return Страница продуктов.
     */
    @Override
    public Page<Product> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> products = productRepository.findAll();
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