package ru.alexey.site.service;
/* 
08.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.alexey.site.dto.UserRegisterRequestDto;
import ru.alexey.site.dto.UserResponseDto;
import ru.alexey.site.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService{
    List<UserResponseDto> findAll();

    UserResponseDto findByIdAndCastToResponse(Long id);

    User findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    UserResponseDto update(Long id, UserRegisterRequestDto userRequest);

    UserResponseDto save(UserRegisterRequestDto userRequest);

    List<User> saveAll(List<User> users);

    void removeUserById(long id);

}
