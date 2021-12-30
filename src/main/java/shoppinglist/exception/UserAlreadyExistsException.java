package shoppinglist.exception;

public class UserAlreadyExistsException extends AppException {

    public UserAlreadyExistsException(String username) {
        super("User with username " + username + " already exists.");
    }
}