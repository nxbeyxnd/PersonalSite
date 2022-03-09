package ru.alexey.site.dto;
/* 
10.03.2022: Alexey created this file inside the package: ru.alexey.site.dto 
*/

import ru.alexey.site.entity.Role;
import ru.alexey.site.entity.User;

public class UserResponseDto {
    private String username;
    private Role role;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
