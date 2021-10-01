package shopinglist.controller;

import org.springframework.web.bind.annotation.*;
import shopinglist.dto.ShoppingListCreateDto;
import shopinglist.dto.ShoppingListDto;
import shopinglist.dto.ShoppingListUpdateDto;

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