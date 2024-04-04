package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с пользователями.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Находит пользователя по его имени.
     *
     * @param name имя пользователя
     * @return объект Optional, содержащий найденного пользователя, если он существует
     */
    Optional<User> findByName(String name);

    /**
     * Находит пользователя по его имени и паролю.
     *
     * @param name     имя пользователя
     * @param password пароль пользователя
     * @return объект Optional, содержащий найденного пользователя, если он существует
     */
    Optional<User> findByNameAndPassword(String name, String password);

    /**
     * Находит пользователя по его электронной почте.
     *
     * @param email электронная почта пользователя
     * @return объект Optional, содержащий найденного пользователя, если он существует
     */
    Optional<User> findByEmail(String email);

    /**
     * Проверяет существование пользователя с указанной электронной почтой.
     *
     * @param email электронная почта пользователя
     * @return true, если пользователь с указанной почтой существует, в противном случае - false
     */
    boolean existsByEmail(String email);

}
