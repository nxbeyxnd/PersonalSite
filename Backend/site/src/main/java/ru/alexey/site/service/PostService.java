package ru.alexey.site.service;/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import ru.alexey.site.dto.PostCreateRequestDto;
import ru.alexey.site.dto.PostResponseDto;
import ru.alexey.site.entity.Post;

import java.util.List;

public interface PostService {
    List<PostResponseDto> findAllPosts();
    Post findById(Long id);
    Post addNewPost(PostCreateRequestDto postCreateRequestDto);
    Post updatePost(PostCreateRequestDto postCreateRequestDto);
    void deletePostById(Long id);
}
