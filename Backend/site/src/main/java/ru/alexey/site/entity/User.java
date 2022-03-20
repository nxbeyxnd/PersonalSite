package ru.alexey.site.entity;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.model 
*/

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = User.TABLE_USER)
public class User {
    static final String TABLE_USER = "USERS";
    private static final String SEQUENCE_GENERATOR_USER = TABLE_USER + "_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_USER)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_USER, sequenceName = SEQUENCE_GENERATOR_USER, allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false, columnDefinition = "VARCHAR")
    private String username;

    @Column(name = "PASSWORD", nullable = false, columnDefinition = "VARCHAR")
    private String password;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CHANGED_AT")
    private LocalDateTime changedAt;

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

    public User(String username, String password, Role role, String email, LocalDateTime createdAt, LocalDateTime changedAt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.changedAt = changedAt;
        this.role = role;
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

        public Builder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            User.this.createdAt = createdAt;
            return this;
        }

        public Builder setChangedAt(LocalDateTime changedAt) {
            User.this.changedAt = changedAt;
            return this;
        }

        public User build() {
            return new User(username, password, role, email, createdAt, changedAt);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(createdAt, user.createdAt) && Objects.equals(changedAt, user.changedAt) && Objects.equals(role, user.role) && Objects.equals(enabled, user.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, createdAt, changedAt, role, enabled);
    }
}
