package ru.alexey.site.service;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.stereotype.Service;
import ru.alexey.site.dto.UserRegisterRequestDto;
import ru.alexey.site.dto.UserResponseDto;
import ru.alexey.site.exception.EntityNotFoundException;
import ru.alexey.site.entity.User;
import ru.alexey.site.repository.RoleRepository;
import ru.alexey.site.repository.UserRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with id (%d) doesn't exists", id)));
    }

    //FIXME: inc id isn't work
    @Override
    public UserResponseDto addNewUser(UserRegisterRequestDto userRequest) {
        User user =
                User.newBuilder()
                        .setUsername(userRequest.getUsername())
                        .setPassword(userRequest.getPassword())
                        .setRole(roleRepository.findByName(
                                userRequest.getRole().toUpperCase(Locale.ROOT)).orElseThrow(
                                () -> new EntityNotFoundException(
                                        String.format("Entity with name (%s) doesn't exist", userRequest.getRole()))
                        )).build();

        return new UserResponseDto(userRepository.save(user));
    }

    @Override
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }
}
