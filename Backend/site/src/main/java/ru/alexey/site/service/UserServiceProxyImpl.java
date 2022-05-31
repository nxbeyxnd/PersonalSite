package ru.alexey.site.service;
/* 
04.04.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.alexey.site.dto.UserRegisterRequestDto;
import ru.alexey.site.dto.UserResponseDto;
import ru.alexey.site.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@Primary
public class UserServiceProxyImpl implements UserService {
    private final UserService userService;

    private final HashMap<Long, UserResponseDto> cacheAll = new HashMap<>();

    public UserServiceProxyImpl(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserResponseDto> findAll() {
        updateCache();
        return new ArrayList<>(cacheAll.values());
    }

    @Override
    public UserResponseDto findByIdAndCastToResponse(Long id) {
        updateCache();
        if (cacheAll.containsKey(id)) return cacheAll.get(id);
        return userService.findByIdAndCastToResponse(id);
    }

    @Override
    public User findUserById(Long id) {
        updateCache();
        return userService.findUserById(id);
    }

    @Override
    public UserResponseDto update(Long id, UserRegisterRequestDto userRequest) {
        updateCache();
        UserResponseDto user = userService.update(id, userRequest);
        cacheAll.put(user.getId(), user);
        return user;
    }

    @Override
    public UserResponseDto save(UserRegisterRequestDto userRequest) {
        updateCache();
        UserResponseDto user = userService.save(userRequest);
        cacheAll.put(user.getId(), user);
        return user;
    }

    //TODO finish that method
    @Override
    public List<User> saveAll(List<User> users) {
        updateCache();
        return Collections.emptyList();
    }

    @Override
    public void removeUserById(long id) {
        updateCache();
        userService.removeUserById(id);
        cacheAll.remove(id);
    }

    private void updateCache() {
        if (cacheAll.isEmpty()) {
            userService.findAll()
                    .forEach(x -> cacheAll.put(x.getId(), x));
        }
    }
}
