package shopinglist.service.mapper;

import org.springframework.stereotype.Component;
import shopinglist.domain.User;
import shopinglist.dto.UserDto;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getUsername());
    }
}