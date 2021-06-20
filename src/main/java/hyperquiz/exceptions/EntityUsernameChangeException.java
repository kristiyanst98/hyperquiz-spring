package hyperquiz.exceptions;

public class EntityUsernameChangeException extends RuntimeException{
    public EntityUsernameChangeException() {
    }

    public EntityUsernameChangeException(String message) {
        super(message);
    }

    public EntityUsernameChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityUsernameChangeException(Throwable cause) {
        super(cause);
    }
}
