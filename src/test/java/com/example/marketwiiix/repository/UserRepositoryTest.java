package com.example.marketwiiix.repository;

import com.example.marketwiiix.MarketWiiixApplication;
import com.example.marketwiiix.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульные тесты для репозитория UserRepository.
 */
@SpringBootTest(classes = MarketWiiixApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    /**
     * Проверка сохранения пользователя.
     */
    @Test
    public void testSaveUser() {
        User user = User.builder()
                .name("Ivan")
                .email("Ivan@gmail.com")
                .password("qwerty1990")
                .birthDate(LocalDate.of(1980, 5, 1))
                .balance(BigDecimal.valueOf(0.9))
                .build();
        User savedUser = userRepository.save(user);
        Optional<User> existedUser = userRepository.findById(savedUser.getUserId());
        assertEquals(savedUser, existedUser.get());
    }

    /**
     * Проверка поиска пользователя по идентификатору.
     */
    @Test
    public void testFindById() {
        int id = 1;
        User actualUser = User.builder()
                .userId(1)
                .name("Lisa99")
                .password("rewq2")
                .email("Lisa99@gmail.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .balance(BigDecimal.valueOf(0.5))
                .orders(new HashSet<>())
                .build();

        Optional<User> expectedUser = userRepository.findById(id);
        assertEquals(expectedUser.get(), actualUser);
    }

    /**
     * Проверка поиска пользователя по имени.
     */
    @Test
    public void testFindByName() {
        String name = "Lisa99";
        User actualUser = User.builder()
                .userId(1)
                .name("Lisa99")
                .password("rewq2")
                .email("Lisa99@gmail.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .balance(BigDecimal.valueOf(0.5))
                .orders(new HashSet<>())
                .build();

        Optional<User> expectedUser = userRepository.findByName(name);
        assertEquals(expectedUser.get(), actualUser);
    }

    /**
     * Проверка поиска пользователя по электронной почте.
     */
    @Test
    void testFindByEmail() {
        String email = "Lisa99@gmail.com";
        User actualUser = User.builder()
                .userId(1)
                .name("Lisa99")
                .password("rewq2")
                .email("Lisa99@gmail.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .balance(BigDecimal.valueOf(0.5))
                .orders(new HashSet<>())
                .build();

        Optional<User> expectedUser = userRepository.findByEmail(email);
        assertEquals(expectedUser.get(), actualUser);
    }

    /**
     * Проверка существования пользователя по электронной почте (должно вернуть true).
     */
    @Test
    void testExistsByEmailShouldReturnTrue() {
        String email = "Lisa99@gmail.com";
        boolean isExist = userRepository.existsByEmail(email);
        assertTrue(isExist);
    }

    /**
     * Проверка существования пользователя по электронной почте (должно вернуть false).
     */
    @Test
    void testExistsByEmailShouldReturnFalse() {
        String email = "Lisa99@@mail.com";
        boolean isExist = userRepository.existsByEmail(email);
        assertFalse(isExist);
    }
}

