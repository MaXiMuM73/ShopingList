package shoppinglist.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import shoppinglist.annotation.LoggableTimeSpentOnMethods;
import shoppinglist.controller.UserController;
import shoppinglist.dto.UserCreateDto;
import shoppinglist.dto.UserDto;
import shoppinglist.dto.UserUpdateDto;
import shoppinglist.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@LoggableTimeSpentOnMethods
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @Override
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        return userService.update(id, userUpdateDto);
    }

    @Override
    public UserDto delete(Long id) {
        return userService.delete(id);
    }
}