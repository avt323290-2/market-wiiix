package com.example.marketwiiix.service;

import com.example.marketwiiix.dto.RegistrationFormDto;
import com.example.marketwiiix.entity.User;

import java.util.List;

/**
 * Сервис пользователей.
 */
public interface UserService {

    /**
     * Получить всех пользователей.
     *
     * @return Список всех пользователей.
     */
    List<User> findAll();

    /**
     * Зарегистрировать нового пользователя.
     *
     * @param registrationFormDto Данные формы регистрации.
     */
    void register(RegistrationFormDto registrationFormDto);
}