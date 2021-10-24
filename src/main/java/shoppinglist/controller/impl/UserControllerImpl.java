package shoppinglist.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import shoppinglist.annotation.LoggableTimeSpentOnMethods;
import shoppinglist.controller.UserController;
import shoppinglist.dto.user.UserCreateDto;
import shoppinglist.dto.user.UserDto;
import shoppinglist.dto.user.UserUpdateDto;
import shoppinglist.dto.user.UserWithRolesDto;
import shoppinglist.dto.user.filter.UserFilterDto;
import shoppinglist.service.UserService;

import java.util.Collection;
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

    @Override
    public void editRoles(String email,
                          Collection<String> newRoleCodes) {
        Long userId = userService.getId(email);

        userService.editRole(userId, newRoleCodes);
    }

    @Override
    public List<UserWithRolesDto> getUsers() {
        return userService.getUsers();
    }

    @Override
    public List<UserWithRolesDto> getUsers(Collection<UserFilterDto> filters) {
        return userService.getUsers(filters);
    }
}