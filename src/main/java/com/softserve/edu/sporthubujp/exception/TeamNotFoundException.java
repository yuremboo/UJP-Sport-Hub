package com.softserve.edu.sporthubujp.exception;

import javax.persistence.PersistenceException;

public class TeamNotFoundException extends PersistenceException {
    private static final String TEAM_NOT_FOUND = "Unable to find team.";

    public TeamNotFoundException(String message) {
        super(message.isEmpty() ? TEAM_NOT_FOUND : message);
    }

    public TeamNotFoundException() {
        super(TEAM_NOT_FOUND);
    }
}