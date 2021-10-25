package shoppinglist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppinglist.dto.user.UserCreateDto;
import shoppinglist.dto.user.UserDto;
import shoppinglist.dto.user.UserUpdateDto;
import shoppinglist.dto.user.UserWithRolesDto;
import shoppinglist.dto.user.authentication.UserAuthenticationInfoDto;
import shoppinglist.dto.user.filter.UserFilterDto;
import shoppinglist.entity.Role;
import shoppinglist.entity.User;
import shoppinglist.exception.UserAlreadyExistsException;
import shoppinglist.exception.UserNotFoundException;
import shoppinglist.repository.RoleRepository;
import shoppinglist.repository.UserRepository;
import shoppinglist.repository.specification.UserSpecification;
import shoppinglist.service.UserService;
import shoppinglist.service.mapper.UserMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        isAlreadyExists(userCreateDto);

        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setEmail(userCreateDto.getEmail());
        Set<Role> newRoles = roleRepository.findAllByCodeIn(Collections.singletonList("USER"));
        user.setRoles(newRoles);

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

    @Override
    public Long getId(String email) {
        return userRepository.findOneByEmail(email).getId();
    }

    @Transactional
    @Override
    public void editRole(Long userId, Collection<String> roleCodes) {
        User user = userRepository.findById(userId).orElseThrow();

        Set<Role> newRoles = roleRepository.findAllByCodeIn(roleCodes);

        user.setRoles(newRoles);

        userRepository.save(user);
    }

    @Override
    public List<UserWithRolesDto> getUsers() {
        List<User> users = userRepository.findAllWithRoles();

        return userMapper.mapUserToUserWithRolesDto(users);
    }

    @Override
    public List<UserWithRolesDto> getUsers(Collection<UserFilterDto> filters) {
        List<User> users = userRepository.findAll(UserSpecification.findUsers(filters));
        return userMapper.mapUserToUserWithRolesDto(users);
    }

    @Override
    public Optional<UserAuthenticationInfoDto> findAuthenticationInfo(String email) {
        Optional<User> userOpt = userRepository.findOneWithRolesByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return Optional.of(new UserAuthenticationInfoDto(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map(Role::getCode).collect(Collectors.toSet())
            ));
        } else {
            return Optional.empty();
        }
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