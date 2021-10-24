package shoppinglist.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserUpdateDto {

    private final String username;

    private final String password;
}