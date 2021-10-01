package shopinglist.controller;

import org.springframework.web.bind.annotation.*;
import shopinglist.dto.UserCreateDto;
import shopinglist.dto.UserDto;
import shopinglist.dto.UserUpdateDto;

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
}