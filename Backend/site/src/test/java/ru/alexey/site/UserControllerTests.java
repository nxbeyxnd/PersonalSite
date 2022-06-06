package ru.alexey.site;
/* 
20.03.2022: Alexey created this file inside the package: ru.alexey.site 
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.alexey.site.configuration.ApplicationUserRole;
import ru.alexey.site.controller.UserController;
import ru.alexey.site.dto.UserRegisterRequestDto;
import ru.alexey.site.dto.UserResponseDto;
import ru.alexey.site.entity.Role;
import ru.alexey.site.entity.User;
import ru.alexey.site.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

class UserControllerTests {
    private static UserService userService = Mockito.mock(UserService.class);
    private static UserController userController = new UserController(userService);
    private static List<UserResponseDto> userRepository = new ArrayList<>();

    @BeforeAll
    static void init() {
        Role role = new Role(ApplicationUserRole.USER);
        User user = new User("test",
                "test",
                role,
                "test",
                LocalDateTime.now(),
                LocalDateTime.now());
        user.setId(1L);
        userRepository.add(new UserResponseDto(user));
        Mockito.when(userService.findByIdAndCastToResponse(any(Long.class))).thenReturn(userRepository.get(0));
        Mockito.when(userService.findAll()).thenReturn(userRepository);
        Mockito.when(userService.save(any(UserRegisterRequestDto.class)))
                .thenReturn(userRepository.get(0));
    }


    @Test
    void shouldReturnUserResponseDto_whenFindAllByIdNotNullAndStatusIsOk_thenSucceedTest() {
        ResponseEntity<UserResponseDto> userToAssert = userController.findUserById(1L);
        Assertions.assertEquals(HttpStatus.OK, userToAssert.getStatusCode());
        Assertions.assertNotNull(userToAssert.getBody());
    }

    @Test
    void shouldReturnListOfUserResponseDto_whenFindAllStatusIsOkTest_thenSucceedTest() {
        ResponseEntity<List<UserResponseDto>> userToAssert = userController.findAllUsers();
        Assertions.assertEquals(HttpStatus.OK, userToAssert.getStatusCode());
        Assertions.assertNotNull(userToAssert.getBody());
    }

    @Test
    void shouldReturnUserResponseDto_whenSaveReturnResponseEntityWithStatusOkAndNotNull_thenSucceedTest() {
        ResponseEntity<UserResponseDto> userToAssert = userController.addNewUser(
                new UserRegisterRequestDto("test2", "test2", "test2", "test2"));
        Assertions.assertEquals(HttpStatus.OK, userToAssert.getStatusCode());
        Assertions.assertNotNull(userToAssert.getBody());
    }
}
