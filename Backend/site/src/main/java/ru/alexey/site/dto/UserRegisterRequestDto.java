package ru.alexey.site.dto;
/* 
10.03.2022: Alexey created this file inside the package: ru.alexey.site.dto 
*/

public class UserRegisterRequestDto {
    private String username;
    private String password;
    private String role;

    public UserRegisterRequestDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
