package shopinglist.controller;

import org.springframework.web.bind.annotation.*;
import shopinglist.dto.ShoppingListCreateDto;
import shopinglist.dto.ShoppingListDeleteDto;
import shopinglist.dto.ShoppingListDto;
import shopinglist.dto.ShoppingListUpdateDto;

@RequestMapping("list")
public interface ShoppingListController {

    @PostMapping
    ShoppingListCreateDto create(@RequestBody ShoppingListCreateDto shoppingListCreateDto);

    @GetMapping("/{id}")
    ShoppingListDto find(@PathVariable Long id);

    @PutMapping
    ShoppingListUpdateDto update(@RequestBody ShoppingListUpdateDto shoppingListUpdateDto);

    @DeleteMapping
    ShoppingListDeleteDto delete(@RequestBody ShoppingListDeleteDto shoppingListDeleteDto);
}