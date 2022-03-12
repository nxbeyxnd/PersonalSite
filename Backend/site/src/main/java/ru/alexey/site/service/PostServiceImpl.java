package ru.alexey.site.service;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexey.site.dto.PostCreateRequestDto;
import ru.alexey.site.entity.Post;
import ru.alexey.site.entity.Tag;
import ru.alexey.site.exception.EntityAlreadyExistsException;
import ru.alexey.site.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    public PostServiceImpl(PostRepository postRepository, TagService tagService) {
        this.postRepository = postRepository;
        this.tagService = tagService;
    }

    @Override
    public Post addNewPost(PostCreateRequestDto postCreateRequestDto) {
        if (postRepository.findPostByTitle(postCreateRequestDto.getTitle()).isPresent())
            throw new EntityAlreadyExistsException(String
                    .format("Post with title (%s) already exists", postCreateRequestDto.getTitle()));

        //FIXME add setUser(Need principal)
        Post post = Post
                .newBuilder()
                .setTitle(postCreateRequestDto.getTitle())
                .setText(postCreateRequestDto.getText())
                .setCreatedAt(LocalDateTime.now())
                .setTags(addNewTags(postCreateRequestDto.getTags()))
                .build();
        return postRepository.save(post);
    }

    @Override
    public Post findByTitle(String title) {
        return null;
    }

    @Override
    public Post updatePost(PostCreateRequestDto postCreateRequestDto) {
        return null;
    }

    @Override
    public void deletePostById(Long id) {

    }

    private Set<Tag> addNewTags(String[] tags){
        Set<Tag> setOfTags = new HashSet<>();
        for(String s: tags){
            setOfTags.add(tagService.addNewTag(s));
        }
        return setOfTags;
    }
}
