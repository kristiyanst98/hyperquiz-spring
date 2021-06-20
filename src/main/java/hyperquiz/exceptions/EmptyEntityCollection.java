package hyperquiz.exceptions;

public class EmptyEntityCollection extends RuntimeException {
    public EmptyEntityCollection() {
    }

    public EmptyEntityCollection(String message) {
        super(message);
    }

    public EmptyEntityCollection(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyEntityCollection(Throwable cause) {
        super(cause);
    }
}
