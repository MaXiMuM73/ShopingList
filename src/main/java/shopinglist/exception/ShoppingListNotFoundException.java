package shopinglist.exception;

public class ShoppingListNotFoundException extends RuntimeException {

    public ShoppingListNotFoundException(long id) {
        super("Shopping list with id " + id + " not found.");
    }
}