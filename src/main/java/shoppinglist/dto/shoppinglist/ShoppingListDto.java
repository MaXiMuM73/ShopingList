package shoppinglist.dto.shoppinglist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shoppinglist.dto.user.UserDto;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class ShoppingListDto {

    private final Long id;

    private final String title;

    private final String content;

    private final LocalDateTime creationDate;

    private final UserDto userDto;
}