package com.example.marketwiiix.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

/**
 * DTO для регистрационной формы.
 */
@Getter
@Builder
public class RegistrationFormDto {

    /** Имя пользователя. */
    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 20, message = "Имя должно быть от 2 до 20 символов")
    private String name;

    /** Пароль пользователя. */
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 2, max = 20, message = "Пароль должен содержать от 2 до 20 символов")
    private String password;

    /** Электронная почта пользователя. */
    @NotNull
    @Email(message = "Неверный формат электронной почты")
    private String email;

    /** Дата рождения пользователя. */
    @Past(message = "Дата должна быть прошедшая")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}