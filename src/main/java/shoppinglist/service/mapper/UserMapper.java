package shoppinglist.service.mapper;

import org.springframework.stereotype.Component;
import shoppinglist.dto.user.UserWithRolesDto;
import shoppinglist.entity.Role;
import shoppinglist.entity.User;
import shoppinglist.dto.user.UserDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }

    public UserWithRolesDto mapUserToUserWithRolesDto(User user) {
        return new UserWithRolesDto(
                user.getId(),
                user.getEmail(),
                user.getRoles().stream().map(Role::getCode).collect(Collectors.toList())
        );
    }

    public List<UserWithRolesDto> mapUserToUserWithRolesDto(Collection<User> users) {
        return users.stream()
                .map(this::mapUserToUserWithRolesDto)
                .distinct()
                .collect(Collectors.toList());
    }
}