package com.example.marketwiiix.repository;

import com.example.marketwiiix.MarketWiiixApplication;
import com.example.marketwiiix.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Модульные тесты для репозитория CategoryRepository.
 */
@SpringBootTest(classes = MarketWiiixApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Тестирование сохранения категории.
     */
    @Test
    public void testSaveCategory() {
        int id = 1;
        Category category = Category.builder()
                .name("category1")
                .rating(1)
                .build();
        Optional<Category> expectedCategory = categoryRepository.findById(id);
        assertEquals(expectedCategory.orElse(null), category);
    }
    /**
     * Тестирование поиска категории по идентификатору.
     */
    @Test
    public void testFindCategoryById() {
        int id = 1;
        Category category = Category.builder()
                .categoryId(1)
                .name("category1")
                .rating(1)
                .build();
        Optional<Category> expectedCategory = categoryRepository.findById(id);
        assertEquals(expectedCategory.orElse(null), category);
    }
    /**
     * Тестирование поиска всех категорий.
     */
    @Test
    public void testFindAll() {
        List<Category> actualCategories = new ArrayList<>();
        Category category1 = Category.builder()
                .categoryId(1)
                .name("category1")
                .rating(1)
                .build();
        Category category2 = Category.builder()
                .categoryId(2)
                .name("category2")
                .rating(1)
                .build();
        Category category3 = Category.builder()
                .categoryId(3)
                .name("category3")
                .rating(1)
                .build();
        actualCategories.add(category1);
        actualCategories.add(category2);
        actualCategories.add(category3);
        List<Category> expectedCategories = categoryRepository.findAll();
        assertEquals(expectedCategories, actualCategories);
    }

}