package ru.alexey.site.configuration;
/* 
03.03.2022: Alexey created this file inside the package: ru.alexey.site.configuration 
*/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static ru.alexey.site.configuration.ApplicationUserRole.ADMIN;
import static ru.alexey.site.configuration.ApplicationUserRole.USER;

//TODO add login check
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Value("${app-path}")
    private String CONTEXT_PATH;

    public SecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userAdmin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("100"))
                .roles(ADMIN.name())
                .build();
        UserDetails userDefault = User.builder()
                .username("def")
                .password(passwordEncoder.encode("100"))
                .roles(USER.name())
                .build();

        return new InMemoryUserDetailsManager(
                userAdmin,
                userDefault
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(CONTEXT_PATH + "user/admin/**").hasRole(ADMIN.name())
                .mvcMatchers(CONTEXT_PATH + "user/feed").hasRole(USER.name())
                .and().formLogin().permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }
}
