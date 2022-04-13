package ru.alexey.site.mapper;/* 
13.04.2022: Alexey created this file inside the package: ru.alexey.site.mapper 
*/

import org.mapstruct.*;
import ru.alexey.site.dto.UserResponseDto;
import ru.alexey.site.entity.User;

@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring"
)
public interface UserMapper {
    User userView(UserResponseDto userResponseDto);

    UserResponseDto userResponseDtoView(User user);
}
