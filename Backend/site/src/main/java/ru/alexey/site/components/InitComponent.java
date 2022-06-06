package ru.alexey.site.components;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.configuration 
*/

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.alexey.site.configuration.ApplicationUserRole;
import ru.alexey.site.entity.Role;
import ru.alexey.site.entity.User;
import ru.alexey.site.repository.RoleRepository;
import ru.alexey.site.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitComponent implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitComponent(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            List<Role> roles = roleRepository.findAll();
            roles.add(new Role(ApplicationUserRole.ADMIN));
            roles.add(new Role(ApplicationUserRole.MODERATOR));
            roles.add(new Role(ApplicationUserRole.USER));
            roleRepository.saveAll(roles);
        }
        if (userRepository.findAll().size() < 1) {
            List<User> users = new ArrayList<>();
            users.add(
                    new User(
                            "admin",
                            passwordEncoder.encode("100"),
                            roleRepository.getById(1L),
                            "admin@gmail.com",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
            users.add(
                    new User(
                            "moder",
                            passwordEncoder.encode("100"),
                            roleRepository.getById(2L),
                            "moder@gmail.com",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
            users.add(
                    new User(
                            "user",
                            passwordEncoder.encode("100"),
                            roleRepository.getById(3L),
                            "user@gmail.com",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
            userRepository.saveAll(users);
        }
    }
}
