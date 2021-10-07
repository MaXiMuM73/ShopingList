package shoppinglist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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