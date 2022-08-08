package com.softserve.edu.sporthubujp.exception;

import javax.persistence.PersistenceException;

public class EntityNotExistsException extends PersistenceException {
    private static final String ENTITY_NOT_EXISTS = "Unable to find entity.";

    public EntityNotExistsException(String message) {
        super(message.isEmpty() ? ENTITY_NOT_EXISTS : message);
    }

    public EntityNotExistsException() {
        super(ENTITY_NOT_EXISTS);
    }
}
