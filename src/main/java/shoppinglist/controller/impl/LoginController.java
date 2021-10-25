package shoppinglist.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shoppinglist.dto.user.UserCreateDto;
import shoppinglist.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class LoginController {

    private final UserService userService;

    @PostMapping("/registration")
    public String registration(@RequestBody UserCreateDto userCreateDto) {
        userService.create(userCreateDto);
        return "User " + userCreateDto.getUsername() + " created.";
    }
}