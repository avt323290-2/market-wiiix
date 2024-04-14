package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Модульные тесты для репозитория RoleRepository.
 */
@SpringBootTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;
    /**
     * Проверка поиска роли по имени.
     */
    @Test
    void findByName() {
        Role expected = new Role();
        expected.setName("user");

        Role actual = repository.findByName(expected.getName());
        assertEquals(actual, expected);
    }
}