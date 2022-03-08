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

import java.util.List;

@Component
public class InitComponent implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public InitComponent(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Role> roles = roleRepository.findAll();
        roles.add(new Role("ADMIN"));
        roles.add(new Role("MODERATOR"));
        roles.add(new Role("USER"));
        roleRepository.saveAll(roles);
        userRepository.save(new User("admin", "100", roleRepository.getById(1L)));
    }
}
