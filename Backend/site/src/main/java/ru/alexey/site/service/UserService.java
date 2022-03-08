package ru.alexey.site.service;
/* 
08.03.2022: Alexey created this file inside the package: ru.alexey.site.service 
*/

import ru.alexey.site.entity.User;

import java.util.List;

public interface UserService{
    List<User> findAll();
    User findById(Long id);


}
