package daniel.netanel.expensemanager;

/**
 * Our specific exception for this project
 */
public class TransactionException extends Exception {

    public TransactionException() {
        super();
    }

    public TransactionException(String message) {
        super(message);
    }

    /**
     * @param message the message describing the exception
     * @param cause   needed var for the exception
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}

