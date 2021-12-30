package shoppinglist.dto.user.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import shoppinglist.enums.UserField;

import java.util.Collection;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class UserFilterDto {

    private final UserField userField;

    private final Collection<String> values;
}