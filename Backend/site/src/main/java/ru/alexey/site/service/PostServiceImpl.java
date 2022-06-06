package ru.alexey.site.service;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alexey.site.components.memento.PostMemento;
import ru.alexey.site.components.memento.PostSessionHistory;
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

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostSessionHistory postSessionHistory;
    private final TagService tagService;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository,
                           TagService tagService,
                           @Qualifier(value = "Cache") UserService userService,
                           PostSessionHistory postSessionHistory) {
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.userService = userService;
        this.postSessionHistory = postSessionHistory;
    }

    @Override
    public PostResponseDto addNewPost(PostCreateRequestDto postCreateRequestDto) {
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
        return new PostResponseDto(postRepository.save(post));
    }

    @Override
    public List<PostResponseDto> findAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(
                        PostResponseDto::new).toList();
    }

    @Override
    public PostResponseDto findById(Long id) {
        return new PostResponseDto(postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Post with id (%d) doesn't exists", id))
        ));
    }

    @Override
    public PostResponseDto updatePost(Long id, PostCreateRequestDto postCreateRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Post with id (%d) doesn't exists", id))
        );
        postSessionHistory.save(post.saveState());

        post.setTitle(postCreateRequestDto.getTitle());
        post.setText(postCreateRequestDto.getText());
        post.setTags(addNewTags(postCreateRequestDto.getTags()));

        return new PostResponseDto(postRepository.save(post));
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Post with id (%d) doesn't exists", id))
        );
        postRepository.deleteById(post.getId());
    }

    private Set<Tag> addNewTags(String[] tags) {
        Set<Tag> setOfTags = new HashSet<>();
        for (String s : tags) {
            setOfTags.add(tagService.addNewTag(s));
        }
        return setOfTags;
    }

    public PostResponseDto rollbackPost() {
        PostMemento postMemento = postSessionHistory.load().orElseThrow(() ->
                new EntityNotFoundException("Queue is empty"));
        Post post = Post.newBuilder()
                .setId(postMemento.getId())
                .setTitle(postMemento.getTitle())
                .setText(postMemento.getText())
                .setCreatedAt(postMemento.getCreatedAt())
                .setUser(postMemento.getUser())
                .setTags(postMemento.getTags())
                .build();

        return new PostResponseDto(postRepository.save(post));
    }
}
