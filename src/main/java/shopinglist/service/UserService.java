package shopinglist.service;

import shopinglist.dto.UserCreateDto;
import shopinglist.dto.UserDeleteDto;
import shopinglist.dto.UserDto;
import shopinglist.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserDto create(UserCreateDto userCreateDto);

    List<UserDto> findAll();

    UserDto update(Long id, UserUpdateDto userUpdateDto);

    UserDto delete(Long id, UserDeleteDto userDeleteDto);
}