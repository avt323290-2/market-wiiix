package com.example.marketwiiix.repository;

import com.example.marketwiiix.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с ролями пользователей.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Находит роль по ее имени.
     *
     * @param name имя роли
     * @return роль с указанным именем, если она существует, иначе null
     */
    Role findByName(String name);
}