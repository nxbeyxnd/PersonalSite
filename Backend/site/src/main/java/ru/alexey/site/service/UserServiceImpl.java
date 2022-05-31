package ru.alexey.site.service;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.stereotype.Service;
import ru.alexey.site.dto.UserRegisterRequestDto;
import ru.alexey.site.dto.UserResponseDto;
import ru.alexey.site.entity.User;
import ru.alexey.site.exception.EntityAlreadyExistsException;
import ru.alexey.site.exception.EntityNotFoundException;
import ru.alexey.site.mapper.UserMapper;
import ru.alexey.site.repository.RoleRepository;
import ru.alexey.site.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userResponseDtoView)
                .toList();
    }

    @Override
    public UserResponseDto findByIdAndCastToResponse(Long id) {
        return userMapper.userResponseDtoView(userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with id (%d) doesn't exists", id))));
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with id (%d) doesn't exists", id)));
    }

    @Override
    public UserResponseDto update(Long id, UserRegisterRequestDto userRequest) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new EntityNotFoundException(
                    String.format("User with id (%d) doesn't exists", id));
        else if (checkUsernameOnExists(userRequest.getUsername()))
            throw new EntityAlreadyExistsException(
                    String.format("User with username (%s) already exists", userRequest.getUsername()));
        user.get().setUsername(userRequest.getUsername());
        user.get().setEmail(userRequest.getEmail());
        user.get().setPassword(userRequest.getPassword());
        user.get().setChangedAt(LocalDateTime.now());
        return userMapper.userResponseDtoView(userRepository.save(user.get()));
    }

    @Override
    public UserResponseDto save(UserRegisterRequestDto userRequest) {
        if (userRepository.findUserByUsername(userRequest.getUsername()).isPresent())
            throw new EntityAlreadyExistsException(String
                    .format("User with username (%S) already exists", userRequest.getUsername()));
        User user =
                User.newBuilder()
                        .setUsername(userRequest.getUsername())
                        .setPassword(userRequest.getPassword())
                        .setRole(roleRepository.findByName(
                                userRequest.getRole().toUpperCase(Locale.ROOT)).orElseThrow(
                                () -> new EntityNotFoundException(
                                        String.format("Entity with name (%s) doesn't exist", userRequest.getRole()))))
                        .setEmail(userRequest.getEmail())
                        .setCreatedAt(LocalDateTime.now())
                        .setChangedAt(LocalDateTime.now())
                        .build();
        return userMapper.userResponseDtoView(userRepository.save(user));
    }

    @Override
    public List<User> saveAll(List<User> users) {
        List<User> toAdd = users.stream()
                .filter(
                        x -> checkEmailOnExists(x.getEmail()) && checkUsernameOnExists(x.getUsername()))
                .toList();
        userRepository.saveAll(toAdd);
        return toAdd;
    }

    @Override
    public void removeUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with id (%d) doesn't exists", id))
        );
        userRepository.deleteById(user.getId());
    }

    private boolean checkUsernameOnExists(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    private boolean checkEmailOnExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }
}
