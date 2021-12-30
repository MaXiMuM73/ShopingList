package shoppinglist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppinglist.dto.shoppinglist.ShoppingListCreateDto;
import shoppinglist.dto.shoppinglist.ShoppingListDto;
import shoppinglist.dto.shoppinglist.ShoppingListUpdateDto;
import shoppinglist.entity.ShoppingList;
import shoppinglist.entity.User;
import shoppinglist.exception.ShoppingListNotFoundException;
import shoppinglist.exception.UserAccessDeniedException;
import shoppinglist.repository.ShoppingListRepository;
import shoppinglist.service.ShoppingListService;
import shoppinglist.service.UserService;
import shoppinglist.service.mapper.ShoppingListMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    private final ShoppingListMapper shoppingListMapper;

    private final UserService userService;

    @Transactional
    @Override
    public ShoppingListDto create(Long userId, ShoppingListCreateDto shoppingListCreateDto) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setTitle(shoppingListCreateDto.getTitle());
        shoppingList.setContent(shoppingListCreateDto.getContent());
        shoppingList.setUser(userService.find(userId));

        return shoppingListMapper
                .mapToShoppingListDto(
                        shoppingListRepository
                                .saveAndFlush(shoppingList));
    }

    @Override
    public List<ShoppingListDto> findAll(String principalName, Long userId) {
        Long principalId = userService.getId(principalName);
        User user = userService.find(principalId);

        if (!Objects.equals(user.getId(), userId)) {
            throw new UserAccessDeniedException();
        }

        List<ShoppingList> allLists =
                shoppingListRepository
                        .findAllByUser(
                                userService
                                        .find(userId));

        return allLists
                .stream()
                .map(shoppingListMapper::mapToShoppingListDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ShoppingListDto update(Long userId, Long id, ShoppingListUpdateDto shoppingListUpdateDto) {
        ShoppingList shoppingList = getShoppingList(userService.find(userId), id);

        shoppingList.setTitle(shoppingListUpdateDto.getTitle());
        shoppingList.setContent(shoppingListUpdateDto.getContent());

        return shoppingListMapper
                .mapToShoppingListDto(
                        shoppingListRepository
                                .saveAndFlush(shoppingList));
    }

    @Transactional
    @Override
    public ShoppingListDto delete(Long userId, Long id) {
        ShoppingList shoppingList = getShoppingList(userService.find(userId), id);

        shoppingListRepository.delete(shoppingList);

        return shoppingListMapper
                .mapToShoppingListDto(shoppingList);
    }

    private ShoppingList getShoppingList(User user, Long id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findByUserAndId(user, id);
        return shoppingList
                .orElseThrow(() -> new ShoppingListNotFoundException(id));
    }
}