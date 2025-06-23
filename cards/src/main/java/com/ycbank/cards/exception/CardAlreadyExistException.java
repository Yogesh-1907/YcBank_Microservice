package com.ycbank.cards.exception;

public class CardAlreadyExistException extends RuntimeException {

    public CardAlreadyExistException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
