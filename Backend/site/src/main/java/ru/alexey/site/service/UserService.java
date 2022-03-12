package ru.alexey.site.service;
/* 
08.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import ru.alexey.site.dto.UserRegisterRequestDto;
import ru.alexey.site.dto.UserResponseDto;

import java.util.List;

public interface UserService{
    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto update(Long id, UserRegisterRequestDto userRequest);
    UserResponseDto save(UserRegisterRequestDto userRequest);
    void removeUserById(long id);

}
