package shoppinglist.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shoppinglist.entity.ShoppingList;
import shoppinglist.dto.shoppinglist.ShoppingListDto;

@Component
@RequiredArgsConstructor
public class ShoppingListMapper {

    private final UserMapper userMapper;

    public ShoppingListDto mapToShoppingListDto(ShoppingList shoppingList) {
        return new ShoppingListDto(
                shoppingList.getId(),
                shoppingList.getTitle(),
                shoppingList.getContent(),
                shoppingList.getCreationDate(),
                userMapper.mapToUserDto(shoppingList.getUser()));
    }
}