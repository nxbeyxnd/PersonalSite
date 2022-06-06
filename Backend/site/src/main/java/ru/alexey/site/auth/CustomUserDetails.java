package ru.alexey.site.auth;
/* 
04.06.2022: Alexey created this file inside the package: ru.alexey.site.auth 
*/

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.alexey.site.entity.User;

import java.util.Collection;

//FIXME Change true hardcoded values.
public class CustomUserDetails implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public CustomUserDetails(User user) {
        this.user = user;
        this.authorities = user.getRole().getAuthority().getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
