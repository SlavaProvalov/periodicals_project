package exceptions;

/**
 * Created by Provalov on 25.09.2017.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
