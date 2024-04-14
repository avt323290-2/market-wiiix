package com.example.marketwiiix.entity.converter;

import com.example.marketwiiix.dto.RegistrationFormDto;
import com.example.marketwiiix.entity.User;
import com.example.marketwiiix.MarketWiiixApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Модульные тесты для пользовательского класса Converterclass.
 */
@SpringBootTest(classes = MarketWiiixApplication.class)
class UserConverterTest {
    @Autowired
    private UserConverter userConverter;

    /**
     * Протестируйте преобразование из сущности пользователя в регистрационную форму.
     */
    @Test
    void convertToDto() {
        User user = User.builder()
                .name("MihailSmirnov")
                .password("123298qq")
                .email("MihailSmirnov@gmail.com")
                .birthDate(LocalDate.of(1988, 1, 1))
                .build();
        // Ожидаемый результат конвертации
        RegistrationFormDto actual = RegistrationFormDto.builder()
                .name("MihailSmirnov")
                .password("123298qq")
                .email("MihailSmirnov@gmail.com")
                .birthDate(LocalDate.of(1988, 1, 1))
                .build();
        RegistrationFormDto expected = userConverter.convertToRegisterFormDto(user);
        assertEquals(actual, expected);
    }

    /**
     * Проверьте преобразование RegistrationFormDto в объект User.
     */
    @Test
    void convertToEntity() {
        RegistrationFormDto registrationFormDto = RegistrationFormDto.builder()
                .name("Irina99")
                .password("qertyQQ1")
                .email("IrinaIr@mail.ru")
                .birthDate(LocalDate.of(1990, 12, 11))
                .build();
        User actual = User.builder()
                .name("Irina99")
                .password("qertyQQ1")
                .email("IrinaIr@mail.ru")
                .birthDate(LocalDate.of(1990, 12, 11))
                .orders(new HashSet<>())
                .build();
        User expected = userConverter.convertRegisterToEntity(registrationFormDto);
        assertEquals(actual, expected);
    }

}