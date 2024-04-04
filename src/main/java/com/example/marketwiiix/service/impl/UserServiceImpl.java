package com.example.marketwiiix.service.impl;

import com.example.marketwiiix.dto.RegistrationFormDto;
import com.example.marketwiiix.entity.Role;
import com.example.marketwiiix.entity.User;
import com.example.marketwiiix.entity.model.CustomUserDetail;
import com.example.marketwiiix.exceptions.UserNotFoundException;
import com.example.marketwiiix.repository.RoleRepository;
import com.example.marketwiiix.repository.UserRepository;
import com.example.marketwiiix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Реализация сервиса пользователей.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    private UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Получить всех пользователей.
     *
     * @return Список всех пользователей.
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Зарегистрировать нового пользователя.
     *
     * @param registrationFormDto Данные формы регистрации.
     */
    @Override
    public void register(RegistrationFormDto registrationFormDto) {
        Role role = roleRepository.findByName("ROLE_USER");
        if (!isExist(registrationFormDto.getEmail()) && role != null) {
            User user = User.builder()
                    .name(registrationFormDto.getName())
                    .password(passwordEncoder.encode(registrationFormDto.getPassword()))
                    .email(registrationFormDto.getEmail())
                    .birthDate(registrationFormDto.getBirthDate())
                    .balance(BigDecimal.valueOf(0.0))
                    .role(role)
                    .build();
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Неверные учетные данные");
        }
    }

    /**
     * Загрузить пользователя по его идентификатору (имени).
     *
     * @param name Имя пользователя.
     * @return Детали пользователя.
     * @throws UserNotFoundException Если пользователь не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UserNotFoundException {
        User user = userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        return new CustomUserDetail(user);
    }

    /**
     * Проверить, существует ли пользователь с данным email.
     *
     * @param email Email пользователя.
     * @return true, если пользователь существует, в противном случае - false.
     */
    private boolean isExist(String email) {
        return userRepository.existsByEmail(email);
    }
}