package ru.alexey.site.controller;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.controller 
*/

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexey.site.dto.PostCreateRequestDto;
import ru.alexey.site.dto.PostResponseDto;
import ru.alexey.site.entity.Post;
import ru.alexey.site.service.PostService;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<PostResponseDto>> findAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PostResponseDto> findPostById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping(path = "")
    public ResponseEntity<PostResponseDto> addNewPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        return ResponseEntity.ok(postService.addNewPost(postCreateRequestDto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PostResponseDto> updatePostById(@PathVariable(name = "id") Long id,
                                                          @RequestBody PostCreateRequestDto postCreateRequestDto) {
        return ResponseEntity.ok(postService.updatePost(id, postCreateRequestDto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<PostResponseDto> deleteById(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }
}
