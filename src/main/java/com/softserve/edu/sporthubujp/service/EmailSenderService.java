package com.softserve.edu.sporthubujp.service;

import javax.mail.SendFailedException;

public interface EmailSenderService {
    /**
     * Method for sending an email letter to a user
     *
     * @param to represents e-mail address of a recipient
     * @param email is an actual email letter
     * @throws SendFailedException
     */
    void send(String to, String email) throws SendFailedException;
}
