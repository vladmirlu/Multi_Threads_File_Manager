package com.multithreads.manager.management.exception;

/**
 * Exception that is thrown in case of constants invalidity.
 */
public class InvalidCommandException extends Exception {

    /**
     * Initializes message variable in the superclass.
     *
     * @param message exception message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}