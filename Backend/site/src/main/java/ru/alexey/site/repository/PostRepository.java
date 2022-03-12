package ru.alexey.site.repository;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.repository 
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alexey.site.entity.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p " +
            "FROM Post p " +
            "WHERE p.title = ?1")
    Optional<Post> findPostByTitle(String title);
}
