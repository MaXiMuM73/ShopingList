package shopinglist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class UserCreateDto {

    private final String username;

    private final String password;
}