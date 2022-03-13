package ru.alexey.site.service;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexey.site.dto.PostCreateRequestDto;
import ru.alexey.site.dto.PostResponseDto;
import ru.alexey.site.entity.Post;
import ru.alexey.site.entity.Tag;
import ru.alexey.site.exception.EntityAlreadyExistsException;
import ru.alexey.site.exception.EntityNotFoundException;
import ru.alexey.site.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private TagService tagService;
    private UserService userService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PostServiceImpl(PostRepository postRepository, TagService tagService, UserService userService) {
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public Post addNewPost(PostCreateRequestDto postCreateRequestDto) {
        if (postRepository.findPostByTitle(postCreateRequestDto.getTitle()).isPresent())
            throw new EntityAlreadyExistsException(String
                    .format("Post with title (%s) already exists", postCreateRequestDto.getTitle()));

        Post post = Post
                .newBuilder()
                .setTitle(postCreateRequestDto.getTitle())
                .setText(postCreateRequestDto.getText())
                .setCreatedAt(LocalDateTime.now())
                .setTags(addNewTags(postCreateRequestDto.getTags()))
                .setUser(userService.findUserById(1L))
                .build();
        return postRepository.save(post);
    }

    @Override
    public List<PostResponseDto> findAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(
                        PostResponseDto::new).collect(Collectors.toList()
                );
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Post with id (%d) doesn't exists", id))
        );
    }

    @Override
    public Post updatePost(PostCreateRequestDto postCreateRequestDto) {
        return null;
    }

    @Override
    public void deletePostById(Long id) {

    }

    private Set<Tag> addNewTags(String[] tags) {
        Set<Tag> setOfTags = new HashSet<>();
        for (String s : tags) {
            setOfTags.add(tagService.addNewTag(s));
        }
        return setOfTags;
    }
}
