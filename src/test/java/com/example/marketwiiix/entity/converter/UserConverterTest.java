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
 * Unit tests for the UserConverter class.
 */
@SpringBootTest(classes = MarketWiiixApplication.class)
class UserConverterTest {
    @Autowired
    private UserConverter userConverter;

    /**
     * Test the conversion from User entity to RegistrationFormDto.
     */
    @Test
    void convertToDto() {
        User user = User.builder()
                .name("Wade")
                .password("Williams")
                .email("williams@mail.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();
        RegistrationFormDto actual = RegistrationFormDto.builder()
                .name("Wade")
                .password("Williams")
                .email("williams@mail.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();
        RegistrationFormDto expected = userConverter.convertToRegisterFormDto(user);
        assertEquals(actual, expected);
    }

    /**
     * Test the conversion from RegistrationFormDto to User entity.
     */
    @Test
    void convertToEntity() {
        RegistrationFormDto registrationFormDto = RegistrationFormDto.builder()
                .name("Wade")
                .password("Williams")
                .email("williams@mail.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();
        User actual = User.builder()
                .name("Wade")
                .password("Williams")
                .email("williams@mail.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .orders(new HashSet<>())
                .build();
        User expected = userConverter.convertRegisterToEntity(registrationFormDto);
        assertEquals(actual, expected);
    }

}