package ru.alexey.site.service;/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import ru.alexey.site.dto.PostCreateRequestDto;
import ru.alexey.site.entity.Post;

public interface PostService {
    Post addNewPost(PostCreateRequestDto postCreateRequestDto);
    Post findByTitle(String title);
    Post updatePost(PostCreateRequestDto postCreateRequestDto);
    void deletePostById(Long id);
}
