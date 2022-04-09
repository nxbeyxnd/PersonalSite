package ru.alexey.site.configuration;
/* 
03.03.2022: Alexey created this file inside the package: ru.alexey.site.configuration 
*/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static ru.alexey.site.configuration.ApplicationUserRole.*;

//TODO add login check
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Value("${app-path}")
    private String CONTEXT_PATH;
    @Value("${key}")
    private String KEY_SECRET;

    public SecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userAdmin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("100"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getAuthorities())
                .build();
        UserDetails userModer = User.builder()
                .username("mod")
                .password(passwordEncoder.encode("100"))
//                .roles(MODERATOR.name())
                .authorities(MODERATOR.getAuthorities())
                .build();
        UserDetails userDefault = User.builder()
                .username("def")
                .password(passwordEncoder.encode("100"))
//                .roles(USER.name())
                .authorities(USER.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                userAdmin,
                userModer,
                userDefault
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/feed", true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7))
                    .key(KEY_SECRET)
                    .rememberMeParameter("remember-me")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "XSRF-TOKEN", "remember-me")
                    .logoutSuccessUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }
}
