package ru.alexey.site.repository;/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.repository 
*/

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexey.site.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
