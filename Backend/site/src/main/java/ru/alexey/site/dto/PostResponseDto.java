package ru.alexey.site.dto;
/* 
13.03.2022: Alexey created this file inside the package: ru.alexey.site.dto 
*/

import ru.alexey.site.entity.Post;
import ru.alexey.site.entity.Tag;

import java.util.Set;
import java.util.stream.Collectors;

public class PostResponseDto {
    private String title;
    private String text;
    private Set<String> tags;
    private UserResponseDto user;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.text = post.getText();
        this.tags = post.getTags()
                .stream()
                .map(
                        Tag::getName).collect(Collectors.toSet()
                );
        this.user = new UserResponseDto(post.getUser());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}
