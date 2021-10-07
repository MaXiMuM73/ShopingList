package shoppinglist.service;

import shoppinglist.dto.ShoppingListCreateDto;
import shoppinglist.dto.ShoppingListDto;
import shoppinglist.dto.ShoppingListUpdateDto;

import java.util.List;

public interface ShoppingListService {

    ShoppingListDto create(Long userId, ShoppingListCreateDto shoppingListCreateDto);

    List<ShoppingListDto> findAll(Long userId);

    ShoppingListDto update(Long userId, Long id, ShoppingListUpdateDto shoppingListUpdateDto);

    ShoppingListDto delete(Long userId, Long id);
}