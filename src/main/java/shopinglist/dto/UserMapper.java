package shopinglist.dto;

import org.springframework.stereotype.Component;
import shopinglist.domain.User;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getUsername());
    }
}