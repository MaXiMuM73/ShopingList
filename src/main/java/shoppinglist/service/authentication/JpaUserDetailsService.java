package shoppinglist.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shoppinglist.service.UserService;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private static final String EX_MSG_TMPL_USER_NOT_FOUND = "[email = %s] Пользователь не найден";

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = userService.findAuthenticationInfo(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(EX_MSG_TMPL_USER_NOT_FOUND, username)));

        return User.builder()
                .username(userInfo.getEmail())
                .password(userInfo.getPassword())
                .roles(userInfo.getRoleCodes().toArray(new String[0]))
                .build();
    }
}