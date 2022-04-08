package ru.alexey.site.components.memento;
/* 
08.04.2022: Alexey created this file inside the package: ru.alexey.site.components.memento
*/

import ru.alexey.site.entity.Post;
import ru.alexey.site.entity.Tag;
import ru.alexey.site.entity.User;

import java.time.LocalDateTime;
import java.util.Set;

public class PostMemento {
    private final Long id;
    private final String title;
    private final String text;
    private final LocalDateTime createdAt;
    private final User user;
    private final Set<Tag> tags;

    public PostMemento(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.createdAt = post.getCreatedAt();
        this.user = post.getUser();
        this.tags = post.getTags();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
