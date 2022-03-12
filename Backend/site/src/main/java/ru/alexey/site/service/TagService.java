package ru.alexey.site.service;/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import ru.alexey.site.entity.Tag;

public interface TagService {
    Tag getTagByName(String tagName);
    Tag addNewTag(String tagName);
    Tag updateTag(Long id, String tagName);
    void deleteTagById(Long id);
}
