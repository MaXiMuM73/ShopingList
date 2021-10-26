package shoppinglist.exception;

public class UserNotFoundException extends AppException {

    public UserNotFoundException(long id) {
        super("User with id " + id + " not found.");
    }
}