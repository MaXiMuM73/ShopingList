package shoppinglist.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import shoppinglist.annotation.LoggableTimeSpentOnMethods;
import shoppinglist.controller.ShoppingListController;
import shoppinglist.dto.shoppinglist.ShoppingListCreateDto;
import shoppinglist.dto.shoppinglist.ShoppingListDto;
import shoppinglist.dto.shoppinglist.ShoppingListUpdateDto;
import shoppinglist.service.ShoppingListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@LoggableTimeSpentOnMethods
public class ShoppingListControllerImpl implements ShoppingListController {

    private final ShoppingListService shoppingListService;

    @Override
    public ShoppingListDto create(Long userId, ShoppingListCreateDto shoppingListCreateDto) {
        return shoppingListService.create(userId, shoppingListCreateDto);
    }

    @Override
    public List<ShoppingListDto> findAll(Long userId) {
        return shoppingListService.findAll(userId);
    }

    @Override
    public ShoppingListDto update(Long userId, Long id, ShoppingListUpdateDto shoppingListUpdateDto) {
        return shoppingListService.update(userId, id, shoppingListUpdateDto);
    }

    @Override
    public ShoppingListDto delete(Long userId, Long id) {
        return shoppingListService.delete(userId, id);
    }
}