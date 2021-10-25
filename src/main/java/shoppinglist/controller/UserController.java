package shoppinglist.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shoppinglist.dto.user.UserCreateDto;
import shoppinglist.dto.user.UserDto;
import shoppinglist.dto.user.UserUpdateDto;
import shoppinglist.dto.user.UserWithRolesDto;
import shoppinglist.dto.user.filter.UserFilterDto;

import java.util.Collection;
import java.util.List;

@RequestMapping("/user")
public interface UserController {

    @PostMapping
    UserDto create(@RequestBody UserCreateDto userCreateDto);

    @GetMapping
    List<UserDto> findAll();

    @PutMapping("{id}")
    UserDto update(@PathVariable Long id, UserUpdateDto userUpdateDto);

    @DeleteMapping("{id}")
    UserDto delete(@PathVariable Long id);

    @PostMapping("/{email}/roles")
    void editRoles(@PathVariable String email,
                          @RequestBody Collection<String> newRoleCodes);

    @GetMapping("/withRoles")
    List<UserWithRolesDto> getUsers();

    @PostMapping("/withRolesByFilter")
    List<UserWithRolesDto> getUsers(@RequestBody Collection<UserFilterDto> filters);
}