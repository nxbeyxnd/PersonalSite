package ru.alexey.site.service;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.stereotype.Service;
import ru.alexey.site.exception.EntityNotFoundException;
import ru.alexey.site.entity.User;
import ru.alexey.site.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with id (%d) doesn't exists", id)));
    }
}
