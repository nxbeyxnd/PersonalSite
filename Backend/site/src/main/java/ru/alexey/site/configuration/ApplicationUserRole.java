package ru.alexey.site.configuration;/* 
08.03.2022: Alexey created this file inside the package: ru.alexey.site.configuration 
*/

import com.google.common.collect.Sets;

import java.util.Set;

import static ru.alexey.site.configuration.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    MODERATOR(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(POST_READ,POST_WRITE, USER_READ, USER_WRITE)),
    CREATOR(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
