package shoppinglist.service;

import shoppinglist.dto.shoppinglist.ShoppingListCreateDto;
import shoppinglist.dto.shoppinglist.ShoppingListDto;
import shoppinglist.dto.shoppinglist.ShoppingListUpdateDto;

import java.util.List;

public interface ShoppingListService {

    ShoppingListDto create(Long userId, ShoppingListCreateDto shoppingListCreateDto);

    List<ShoppingListDto> findAll(Long userId);

    ShoppingListDto update(Long userId, Long id, ShoppingListUpdateDto shoppingListUpdateDto);

    ShoppingListDto delete(Long userId, Long id);
}