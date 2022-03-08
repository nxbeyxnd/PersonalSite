package ru.alexey.site.configuration;
/* 
08.03.2022: Alexey created this file inside the package: ru.alexey.site.configuration 
*/

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    POST_READ("post:read"),
    POST_WRITE("post:write");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
