package shopinglist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopinglist.domain.ShoppingList;
import shopinglist.domain.User;
import shopinglist.dto.ShoppingListCreateDto;
import shopinglist.dto.ShoppingListDto;
import shopinglist.dto.ShoppingListUpdateDto;
import shopinglist.exception.ShoppingListNotFoundException;
import shopinglist.repository.ShoppingListRepository;
import shopinglist.service.ShoppingListService;
import shopinglist.service.UserService;
import shopinglist.service.mapper.ShoppingListMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    private final ShoppingListMapper shoppingListMapper;

    private final UserService userService;

    @Override
    public ShoppingListDto create(Long userId, ShoppingListCreateDto shoppingListCreateDto) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setTitle(shoppingListCreateDto.getTitle());
        shoppingList.setContent(shoppingListCreateDto.getContent());
        shoppingList.setUser(userService.find(userId));

        return shoppingListMapper
                .mapToShoppingListDto(
                        shoppingListRepository
                                .save(shoppingList));
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

    @Override
    public ShoppingListDto update(Long userId, Long id, ShoppingListUpdateDto shoppingListUpdateDto) {
        ShoppingList shoppingList = getShoppingList(userService.find(userId), id);

        shoppingList.setTitle(shoppingListUpdateDto.getTitle());
        shoppingList.setContent(shoppingListUpdateDto.getContent());

        return shoppingListMapper
                .mapToShoppingListDto(
                        shoppingListRepository
                                .save(shoppingList));
    }

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