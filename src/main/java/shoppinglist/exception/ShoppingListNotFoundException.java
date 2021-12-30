package shoppinglist.exception;

public class ShoppingListNotFoundException extends AppException {

    public ShoppingListNotFoundException(long id) {
        super("Shopping list with id " + id + " not found.");
    }
}