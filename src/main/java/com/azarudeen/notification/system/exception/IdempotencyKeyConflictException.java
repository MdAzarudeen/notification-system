package com.azarudeen.notification.system.exception;

public class IdempotencyKeyConflictException extends RuntimeException {

    public IdempotencyKeyConflictException(String message) {
        super(message);
    }
}