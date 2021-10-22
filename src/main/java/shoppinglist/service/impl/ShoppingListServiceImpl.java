package shoppinglist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppinglist.domain.ShoppingList;
import shoppinglist.domain.User;
import shoppinglist.dto.ShoppingListCreateDto;
import shoppinglist.dto.ShoppingListDto;
import shoppinglist.dto.ShoppingListUpdateDto;
import shoppinglist.exception.ShoppingListNotFoundException;
import shoppinglist.repository.ShoppingListRepository;
import shoppinglist.service.ShoppingListService;
import shoppinglist.service.UserService;
import shoppinglist.service.mapper.ShoppingListMapper;

import java.util.List;
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
    public List<ShoppingListDto> findAll(Long userId) {
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