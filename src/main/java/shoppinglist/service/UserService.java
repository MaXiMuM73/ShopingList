package shoppinglist.service;

import shoppinglist.dto.user.UserWithRolesDto;
import shoppinglist.dto.user.filter.UserFilterDto;
import shoppinglist.entity.User;
import shoppinglist.dto.user.UserCreateDto;
import shoppinglist.dto.user.UserDto;
import shoppinglist.dto.user.UserUpdateDto;

import java.util.Collection;
import java.util.List;

public interface UserService {

    UserDto create(UserCreateDto userCreateDto);

    List<UserDto> findAll();

    User find(Long id);

    UserDto update(Long id, UserUpdateDto userUpdateDto);

    UserDto delete(Long id);

    Long getId(String email);

    void editRole(Long userId, Collection<String> roleCodes);

    List<UserWithRolesDto> getUsers();

    List<UserWithRolesDto> getUsers(Collection<UserFilterDto> filters);
}