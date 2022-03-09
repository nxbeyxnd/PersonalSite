package ru.alexey.site.entity;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.model 
*/

import javax.persistence.*;

@Entity
@Table(name = User.TABLE_USER)
public class User {
    static final String TABLE_USER = "USERS";
    private static final String SEQUENCE_GENERATOR_USER = TABLE_USER + "_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_USER)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_USER, sequenceName = SEQUENCE_GENERATOR_USER, allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(
            name = "ROLE",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_ID_ROLE_ID_RELATION")
    )
    private Role role;

    @Column(name = "ENABLED")
    private Boolean enabled = true;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        public Builder setUsername(String username) {
            User.this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public Builder setRole(Role role) {
            User.this.role = role;
            return this;
        }

        public User build() {
            return new User(username, password, role);
        }
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
