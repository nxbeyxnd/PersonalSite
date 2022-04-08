package ru.alexey.site.service;/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import ru.alexey.site.dto.PostCreateRequestDto;
import ru.alexey.site.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    List<PostResponseDto> findAllPosts();

    PostResponseDto findById(Long id);

    PostResponseDto addNewPost(PostCreateRequestDto postCreateRequestDto);

    PostResponseDto updatePost(Long id, PostCreateRequestDto postCreateRequestDto);

    void deletePostById(Long id);

    PostResponseDto rollbackPost();
}
