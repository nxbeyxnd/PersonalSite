package ru.alexey.site.dto;
/* 
10.03.2022: Alexey created this file inside the package: ru.alexey.site.dto 
*/

import ru.alexey.site.entity.Role;
import ru.alexey.site.entity.User;

public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Role role;

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
}
