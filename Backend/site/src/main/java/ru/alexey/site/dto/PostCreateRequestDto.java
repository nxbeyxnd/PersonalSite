package ru.alexey.site.dto;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.dto 
*/

public class PostCreateRequestDto {
    private String title;
    private String text;
    private String[] tags;

    public PostCreateRequestDto(String title, String text, String[] tags) {
        this.title = title;
        this.text = text;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
