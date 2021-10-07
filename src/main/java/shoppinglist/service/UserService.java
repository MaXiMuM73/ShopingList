package shoppinglist.service;

import shoppinglist.domain.User;
import shoppinglist.dto.UserCreateDto;
import shoppinglist.dto.UserDto;
import shoppinglist.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserDto create(UserCreateDto userCreateDto);

    List<UserDto> findAll();

    User find(Long id);

    UserDto update(Long id, UserUpdateDto userUpdateDto);

    UserDto delete(Long id);
}