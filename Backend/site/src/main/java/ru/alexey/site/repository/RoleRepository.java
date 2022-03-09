package ru.alexey.site.repository;/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.repository 
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alexey.site.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r " +
            "FROM Role r " +
            "WHERE r.name = ?1")
    Optional<Role> findByName(String roleName);
}
