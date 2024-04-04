package com.example.marketwiiix.exceptions;

/**
 * Исключение, выбрасываемое при отсутствии пользователя.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}