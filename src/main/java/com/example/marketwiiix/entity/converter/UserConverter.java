package com.example.marketwiiix.entity.converter;

import com.example.marketwiiix.dto.RegistrationFormDto;
import com.example.marketwiiix.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс-конвертер для преобразования между сущностями User и объектами RegistrationFormDto.
 */
@Component
public class UserConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Преобразование сущности User в объект RegistrationFormDto.
     * @param user Сущность User для преобразования.
     * @return Объект RegistrationFormDto.
     */
    public RegistrationFormDto convertToRegisterFormDto(User user) {
        return modelMapper.map(user, RegistrationFormDto.class);
    }

    /**
     * Преобразование объекта RegistrationFormDto в сущность User.
     * @param registrationFormDto Объект RegistrationFormDto для преобразования.
     * @return Сущность User.
     */
    public User convertRegisterToEntity(RegistrationFormDto registrationFormDto) {
        return modelMapper.map(registrationFormDto, User.class);
    }
}