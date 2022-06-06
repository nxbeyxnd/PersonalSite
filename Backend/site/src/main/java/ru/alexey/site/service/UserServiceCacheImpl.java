package ru.alexey.site.service;
/* 
04.04.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alexey.site.dto.UserRegisterRequestDto;
import ru.alexey.site.dto.UserResponseDto;
import ru.alexey.site.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service("Cache")
public class UserServiceCacheImpl implements UserService {
    private final UserService userService;

    private final HashMap<Long, UserResponseDto> cacheAll = new HashMap<>();

    public UserServiceCacheImpl(@Qualifier("userServiceImpl") UserService userService) {
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
    public Optional<User> findUserByUsername(String username) {
        return Optional.empty();
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
//        users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        return userService.saveAll(users);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }
}
