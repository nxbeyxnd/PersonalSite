package ru.alexey.site.dto;
/* 
10.03.2022: Alexey created this file inside the package: ru.alexey.site.dto 
*/

import ru.alexey.site.entity.Role;
import ru.alexey.site.entity.User;

import java.util.Objects;

public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Role role;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto that = (UserResponseDto) o;
        return id.equals(that.id) && username.equals(that.username) && email.equals(that.email) && role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, role);
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
