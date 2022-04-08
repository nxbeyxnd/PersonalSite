package ru.alexey.site.controller;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.controller 
*/

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.alexey.site.dto.PostCreateRequestDto;
import ru.alexey.site.dto.PostResponseDto;
import ru.alexey.site.service.PostService;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize(value = "hasAuthority('post:read')")
    @GetMapping(path = "")
    public ResponseEntity<List<PostResponseDto>> findAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @PreAuthorize(value = "hasAuthority('post:write')")
    @GetMapping(path = "{id}")
    public ResponseEntity<PostResponseDto> findPostById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PreAuthorize(value = "hasAuthority('post:write')")
    @PostMapping(path = "")
    public ResponseEntity<PostResponseDto> addNewPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        return ResponseEntity.ok(postService.addNewPost(postCreateRequestDto));
    }

    @PreAuthorize(value = "hasAuthority('post:write')")
    @PutMapping(path = "{id}")
    public ResponseEntity<PostResponseDto> updatePostById(@PathVariable(name = "id") Long id,
                                                          @RequestBody PostCreateRequestDto postCreateRequestDto) {
        return ResponseEntity.ok(postService.updatePost(id, postCreateRequestDto));
    }

    @PreAuthorize(value = "hasAuthority('post:write')")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<PostResponseDto> deleteById(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(value = "hasAuthority('post:write')")
    @PostMapping(path = "undo")
    public ResponseEntity<PostResponseDto> addNewPost() {
        return ResponseEntity.ok(postService.rollbackPost());
    }
}
