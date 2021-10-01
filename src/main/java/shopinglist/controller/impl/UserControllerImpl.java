package shopinglist.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import shopinglist.controller.UserController;
import shopinglist.dto.UserCreateDto;
import shopinglist.dto.UserDeleteDto;
import shopinglist.dto.UserDto;
import shopinglist.dto.UserUpdateDto;
import shopinglist.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
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
    public UserDto delete(Long id, UserDeleteDto userDeleteDto) {
        return userService.delete(id, userDeleteDto);
    }

}