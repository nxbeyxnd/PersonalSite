package ru.alexey.site.entity;
/*
06.03.2022: Alexey created this file inside the package: ru.alexey.site.model
*/

import ru.alexey.site.configuration.ApplicationUserRole;

import javax.persistence.*;

@Entity
@Table(name = Role.TABLE_ROLE)
public class Role {
    static final String TABLE_ROLE = "ROLES";
    private static final String SEQUENCE_GENERATOR_ROLE = TABLE_ROLE + "_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_ROLE)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_ROLE, allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHORITY")
    private ApplicationUserRole authority;

    public Role() {
    }

    public Role(ApplicationUserRole role) {
        this.name = role.name();
        authority = role;
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

    public ApplicationUserRole getAuthority() {
        return authority;
    }

    public void setAuthority(ApplicationUserRole authority) {
        this.authority = authority;
    }

    //    public class Builder {
//
//        public Builder setName(String name) {
//            Role.this.name = name;
//            return this;
//        }
//
//
//        public Role build() {
//            return new Role(name);
//        }
//    }
}
