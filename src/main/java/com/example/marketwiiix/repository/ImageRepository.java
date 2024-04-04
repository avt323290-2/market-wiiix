package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с изображениями продуктов.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    /**
     * Находит изображение по идентификатору продукта.
     *
     * @param productId идентификатор продукта
     * @return объект Optional, содержащий найденное изображение продукта, если оно существует, или пустой, если изображение не найдено
     */
    Optional<Image> findImageByProductId(Integer productId);

}
