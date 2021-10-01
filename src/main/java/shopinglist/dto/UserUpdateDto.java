package shopinglist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserUpdateDto {

    private final String username;

    private final String password;
}