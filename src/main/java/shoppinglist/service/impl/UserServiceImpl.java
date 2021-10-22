package shoppinglist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppinglist.domain.User;
import shoppinglist.dto.UserCreateDto;
import shoppinglist.dto.UserDto;
import shoppinglist.dto.UserUpdateDto;
import shoppinglist.exception.UserAlreadyExistsException;
import shoppinglist.exception.UserNotFoundException;
import shoppinglist.repository.UserRepository;
import shoppinglist.service.UserService;
import shoppinglist.service.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        isAlreadyExists(userCreateDto);

        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());

        return userMapper
                .mapToUserDto(
                        userRepository.saveAndFlush(user));
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
        return getUserById(id);
    }

    @Transactional
    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        User user = getUserById(id);
        user.setUsername(userUpdateDto.getUsername());
        user.setPassword(userUpdateDto.getPassword());

        return userMapper
                .mapToUserDto(
                        userRepository
                                .saveAndFlush(user));
    }

    @Transactional
    @Override
    public UserDto delete(Long id) {
        User user = getUserById(id);

        userRepository.delete(getUserById(id));

        return userMapper.mapToUserDto(user);
    }

    private User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new UserNotFoundException(id));
    }

    private void isAlreadyExists(UserCreateDto userCreateDto) {
        if (userRepository.findByUsername(userCreateDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(userCreateDto.getUsername());
        }
    }
}