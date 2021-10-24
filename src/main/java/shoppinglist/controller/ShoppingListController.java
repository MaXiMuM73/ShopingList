package shoppinglist.controller;

import org.springframework.web.bind.annotation.*;
import shoppinglist.dto.shoppinglist.ShoppingListCreateDto;
import shoppinglist.dto.shoppinglist.ShoppingListDto;
import shoppinglist.dto.shoppinglist.ShoppingListUpdateDto;

import java.util.List;

@RequestMapping("list/{userId}")
public interface ShoppingListController {

    @PostMapping()
    ShoppingListDto create(@PathVariable Long userId,
                           @RequestBody ShoppingListCreateDto shoppingListCreateDto);

    @GetMapping()
    List<ShoppingListDto> findAll(@PathVariable Long userId);

    @PutMapping("{id}")
    ShoppingListDto update(@PathVariable Long userId,
                           @PathVariable Long id,
                           @RequestBody ShoppingListUpdateDto shoppingListUpdateDto);

    @DeleteMapping("{id}")
    ShoppingListDto delete(@PathVariable Long userId,
                           @PathVariable Long id);
}