package ru.alexey.site.service;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.stereotype.Service;
import ru.alexey.site.entity.Tag;
import ru.alexey.site.exception.EntityNotFoundException;
import ru.alexey.site.repository.TagRepository;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getTagByName(String tagName) {
        return tagRepository.findByName(tagName).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Tag with name (%S) doesn't exists", tagName)));
    }

    @Override
    public Tag addNewTag(String tagName) {
        Optional<Tag> tag = tagRepository.findByName(tagName);
        if (tag.isPresent())
            return tag.get();
        return tagRepository.save(new Tag(tagName));
    }

    @Override
    public Tag updateTag(Long id, String tagName) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity with id (%d) doesn't exists", id)));
        tag.setName(tagName);
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }
}
