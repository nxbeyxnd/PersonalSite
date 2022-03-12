package ru.alexey.site.entity;
/* 
12.03.2022: Alexey created this file inside the package: ru.alexey.site.entity 
*/

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = Post.TABLE_POST)
public class Post {
    static final String TABLE_POST = "POSTS";
    private static final String SEQUENCE_GENERATOR_POST = TABLE_POST + "_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_POST)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_POST, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE
    })
    @JoinColumn(
            name = "USER",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_POST_ID_USER_ID_RELATION")
    )
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "POST_TAG_RELATION",
            joinColumns = @JoinColumn(
                    name = "POST_ID",
                    nullable = false
//                    foreignKey = @ForeignKey(
//                            name = "FK_POST_ID_TAG_ID_RELATION",
//                            foreignKeyDefinition = "FOREIGN KEY (POST_ID) REFERENCES POSTS.ID"
//                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "TAG_ID",
                    nullable = false
//                    foreignKey = @ForeignKey(
//                            name = "FK_POST_ID_TAG_ID_RELATION",
//                            foreignKeyDefinition = "FOREIGN KEY (TAG_ID) REFERENCES TAGS.ID"
//                    )
            )
    )
    private Set<Tag> tags;

    public Post() {
    }

    public Post(String title, String text, LocalDateTime createdAt, User user, Set<Tag> tags) {
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.user = user;
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public static Builder newBuilder() {
        return new Post().new Builder();
    }

    public class Builder {
        public Builder setTitle(String title) {
            Post.this.title = title;
            return this;
        }

        public Builder setText(String text) {
            Post.this.text = text;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime timeNow) {
            Post.this.createdAt = timeNow;
            return this;
        }

        public Builder setUser(User user) {
            Post.this.user = user;
            return this;
        }

        public Builder setTags(Set<Tag> tags) {
            Post.this.tags = tags;
            return this;
        }

        public Post build() {
            return new Post(title, text, createdAt, user, tags);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
