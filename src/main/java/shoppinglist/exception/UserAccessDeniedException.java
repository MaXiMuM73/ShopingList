package shoppinglist.exception;

public class UserAccessDeniedException extends RuntimeException {

    public UserAccessDeniedException() {
        super("Access denied.");
    }
}