package shoppinglist.exception;

public class UserAccessDeniedException extends AppException {

    public UserAccessDeniedException() {
        super("Access denied.");
    }
}