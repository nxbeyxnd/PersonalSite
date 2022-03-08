package ru.alexey.site.entity;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.model 
*/

import javax.persistence.*;

@Entity
@Table(name = Role.TABLE_ROLE)
public class Role {
    static final String TABLE_ROLE = "ROLES";
    private static final String SEQUENCE_GENERATOR_ROLE = TABLE_ROLE + "_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_ROLE)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_ROLE, sequenceName = SEQUENCE_GENERATOR_ROLE, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
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

    public void setName(String role) {
        this.name = role;
    }
}