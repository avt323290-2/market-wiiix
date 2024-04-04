package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с категориями продуктов.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}