package shopinglist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopinglist.domain.User;
import shopinglist.dto.UserCreateDto;
import shopinglist.dto.UserDto;
import shopinglist.dto.UserUpdateDto;
import shopinglist.exception.UserNotFoundException;
import shopinglist.repository.UserRepository;
import shopinglist.service.UserService;
import shopinglist.service.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());

        return userMapper
                .mapToUserDto(
                        userRepository.save(user));
    }

    @Override
    public List<UserDto> findAll() {
        List<User> allUsers = userRepository.findAll();

        return allUsers
                .stream()
                .map((userMapper::mapToUserDto))
                .collect(Collectors.toList());
    }

    @Override
    public User find(Long id) {
        return getUser(id);
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        User user = getUser(id);
        user.setUsername(userUpdateDto.getUsername());
        user.setPassword(userUpdateDto.getPassword());

        return userMapper
                .mapToUserDto(
                        userRepository
                                .save(user));
    }

    @Override
    public UserDto delete(Long id) {
        User user = getUser(id);

        userRepository.delete(getUser(id));

        return userMapper.mapToUserDto(user);
    }

    private User getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new UserNotFoundException(id));
    }
}