package shoppinglist.dto.user.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class UserAuthenticationInfoDto {

    private final Long id;

    private final String email;

    private final String password;

    private final Set<String> roleCodes;
}