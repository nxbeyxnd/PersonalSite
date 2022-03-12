package ru.alexey.site.repository;/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.repository 
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alexey.site.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t " +
            "FROM Tag t " +
            "WHERE t.name = ?1")
    Optional<Tag> findByName(String tag);
}
