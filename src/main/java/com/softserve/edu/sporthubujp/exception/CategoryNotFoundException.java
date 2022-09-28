package com.softserve.edu.sporthubujp.exception;

import javax.persistence.PersistenceException;

public class CategoryNotFoundException extends PersistenceException {
    private static final String CATEGORY_NOT_FOUND = "Unable to find category.";

    public CategoryNotFoundException(String message) {
        super(message.isEmpty() ? CATEGORY_NOT_FOUND : message);
    }

    public CategoryNotFoundException() {
        super(CATEGORY_NOT_FOUND);
    }
}
