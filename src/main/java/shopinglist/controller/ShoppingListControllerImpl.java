package shopinglist.controller;

import org.springframework.web.bind.annotation.RestController;
import shopinglist.dto.ShoppingListCreateDto;
import shopinglist.dto.ShoppingListDeleteDto;
import shopinglist.dto.ShoppingListDto;
import shopinglist.dto.ShoppingListUpdateDto;

@RestController
public class ShoppingListControllerImpl implements ShoppingListController {

    @Override
    public ShoppingListCreateDto create(ShoppingListCreateDto shoppingListCreateDto) {
        return shoppingListCreateDto;
    }

    @Override
    public ShoppingListDto find(Long id) {
        return new ShoppingListDto(1L, "Test");
    }

    @Override
    public ShoppingListUpdateDto update(ShoppingListUpdateDto shoppingListUpdateDto) {
        return shoppingListUpdateDto;
    }

    @Override
    public ShoppingListDeleteDto delete(ShoppingListDeleteDto shoppingListDeleteDto) {
        return shoppingListDeleteDto;
    }
}