package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Репозиторий для работы с товарами.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Находит товар по его имени.
     *
     * @param name имя товара
     * @return товар с указанным именем, если он существует, иначе null
     */
    Optional<Product> findByName(String name);

    /**
     * Находит товары по идентификатору категории.
     *
     * @param categoryId идентификатор категории
     * @return список товаров для указанной категории
     */
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId")
    List<Product> findProductsByCategoryId(Integer categoryId);

    /**
     * Находит товары, содержащие в названии или описании указанные ключевые слова (без учета регистра).
     *
     * @param searchKeyName  ключевое слово для поиска в названии товара
     * @param searchKeyDesc ключевое слово для поиска в описании товара
     * @return набор товаров, удовлетворяющих критериям поиска
     */
    Set<Product> findProductsByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String searchKeyName, String searchKeyDesc);

    /**
     * Находит товар по его идентификатору.
     *
     * @param productId идентификатор товара
     * @return товар с указанным идентификатором, если он существует, иначе null
     */
    Optional<Product> findProductByProductId(Integer productId);

    /**
     * Находит все товары для указанной категории с пагинацией.
     *
     * @param categoryId идентификатор категории
     * @param pageable   объект для описания пагинации и сортировки результатов
     * @return страница с товарами для указанной категории
     */
    Page<Product> findAllByCategory_CategoryId(Integer categoryId, Pageable pageable);

}