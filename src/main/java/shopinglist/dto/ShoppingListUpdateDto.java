package shopinglist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class ShoppingListUpdateDto {

    private final String title;

    private final String content;
}