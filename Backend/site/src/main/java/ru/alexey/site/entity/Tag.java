package ru.alexey.site.entity;
/*
12.03.2022: Alexey created this file inside the package: ru.alexey.site.entity 
*/

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = Tag.TABLE_TAG)
public class Tag {
    static final String TABLE_TAG = "TAGS";
    private static final String SEQUENCE_GENERATOR_TAG = TABLE_TAG + "_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_TAG)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_TAG, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "POST_TAG_RELATION",
            joinColumns = @JoinColumn(
                    name = "TAG_ID",
                    nullable = false
//                    foreignKey = @ForeignKey(
//                            name = "FK_POST_ID_TAG_ID_RELATION",
//                            foreignKeyDefinition = "FOREIGN KEY (TAG_ID) REFERENCES TAGS.ID"
//                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "POST_ID",
                    nullable = false
//                    foreignKey = @ForeignKey(
//                            name = "FK_POST_ID_TAG_ID_RELATION",
//                            foreignKeyDefinition = "FOREIGN KEY (POST_ID) REFERENCES POSTS.ID"
//                    )
            )
    )
    private Set<Post> posts;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public void addPost(Post post){
        this.posts.add(post);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
