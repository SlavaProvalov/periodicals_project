package exceptions;

/**
 * Created by Provalov on 25.09.2017.
 */
public class LoginAlreadyExistException extends RuntimeException {
    public LoginAlreadyExistException() {
    }

    public LoginAlreadyExistException(String message) {
        super(message);
    }
}
