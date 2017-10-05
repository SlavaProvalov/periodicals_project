package exceptions;

/**
 * Created by Provalov on 26.09.2017.
 */
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
