package com.vinnichenko.bdepot.exception;

/**
 * The type Transaction exception.
 */
public class TransactionException extends Exception {

    /**
     * Instantiates a new Transaction exception.
     */
    public TransactionException() {
    }

    /**
     * Instantiates a new Transaction exception.
     *
     * @param message the message
     */
    public TransactionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Transaction exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Transaction exception.
     *
     * @param cause the cause
     */
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
