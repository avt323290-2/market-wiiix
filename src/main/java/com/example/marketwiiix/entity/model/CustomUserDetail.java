package com.example.marketwiiix.entity.model;

import com.example.marketwiiix.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

/**
 * Класс CustomUserDetail реализует интерфейс UserDetails и предоставляет информацию о пользователе для Spring Security.
 */
public class CustomUserDetail implements UserDetails {
    private final User user;

    /**
     * Конструктор для создания объекта CustomUserDetail.
     * @param user пользователь, для которого предоставляется информация.
     */
    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Получает идентификатор пользователя.
     * @return Идентификатор пользователя.
     */
    public Integer getUserId() {
        return user.getUserId();
    }

    /**
     * Получает адрес электронной почты пользователя.
     * @return Адрес электронной почты пользователя.
     */
    public String getEmail() {
        return user.getEmail();
    }

    /**
     * Получает роль пользователя.
     * @return Роль пользователя.
     */
    public String getRoleName() {
        return user.getRole().getName();
    }

    /**
     * Получает баланс пользователя.
     * @return Баланс пользователя.
     */
    public BigDecimal getBalance() {
        return user.getBalance();
    }

    /**
     * Получает дату рождения пользователя.
     * @return Дата рождения пользователя.
     */
    public LocalDate getBirthDate() {
        return user.getBirthDate();
    }
}