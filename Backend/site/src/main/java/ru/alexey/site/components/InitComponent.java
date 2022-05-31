package ru.alexey.site.components;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.configuration 
*/

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.alexey.site.entity.Role;
import ru.alexey.site.entity.User;
import ru.alexey.site.repository.RoleRepository;
import ru.alexey.site.repository.UserRepository;
import ru.alexey.site.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitComponent implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserService userService;

    public InitComponent(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Role> roles = roleRepository.findAll();
        roles.add(new Role("ADMIN"));
        roles.add(new Role("MODERATOR"));
        roles.add(new Role("USER"));
        roleRepository.saveAll(roles);
        if (userService.findAll().size() < 3) {
            List<User> users = new ArrayList<>();
            users.add(
                    new User(
                            "admin",
                            "100",
                            roleRepository.getById(1L),
                            "admin@gmail.com",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
            users.add(
                    new User(
                            "moder",
                            "100",
                            roleRepository.getById(2L),
                            "moder@gmail.com",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
            users.add(
                    new User(
                            "user",
                            "100",
                            roleRepository.getById(3L),
                            "user@gmail.com",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
            userService.saveAll(users);
        }
    }
}
