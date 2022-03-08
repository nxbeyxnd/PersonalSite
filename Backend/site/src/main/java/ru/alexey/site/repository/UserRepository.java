package ru.alexey.site.repository;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.repository 
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.site.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
