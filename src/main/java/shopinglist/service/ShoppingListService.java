package shopinglist.service;

import shopinglist.dto.ShoppingListCreateDto;
import shopinglist.dto.ShoppingListDto;
import shopinglist.dto.ShoppingListUpdateDto;

import java.util.List;

public interface ShoppingListService {

    ShoppingListDto create(Long userId, ShoppingListCreateDto shoppingListCreateDto);

    List<ShoppingListDto> findAll(Long userId);

    ShoppingListDto update(Long userId, Long id, ShoppingListUpdateDto shoppingListUpdateDto);

    ShoppingListDto delete(Long userId, Long id);
}