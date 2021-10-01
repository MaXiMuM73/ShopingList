package shopinglist.service;

import shopinglist.domain.User;
import shopinglist.dto.UserCreateDto;
import shopinglist.dto.UserDto;
import shopinglist.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserDto create(UserCreateDto userCreateDto);

    List<UserDto> findAll();

    User find(Long id);

    UserDto update(Long id, UserUpdateDto userUpdateDto);

    UserDto delete(Long id);
}