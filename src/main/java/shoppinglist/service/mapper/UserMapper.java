package shoppinglist.service.mapper;

import org.springframework.stereotype.Component;
import shoppinglist.domain.User;
import shoppinglist.dto.UserDto;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }
}