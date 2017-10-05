package exceptions;

/**
 * Created by Provalov on 25.09.2017.
 */
public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException() {
    }

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
