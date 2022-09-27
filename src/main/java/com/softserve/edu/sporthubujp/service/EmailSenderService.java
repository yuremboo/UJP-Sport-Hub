package com.softserve.edu.sporthubujp.service;

import javax.mail.SendFailedException;

import org.springframework.scheduling.annotation.Async;

public interface EmailSenderService {
    /**
     * Method for sending an email letter to a user
     *
     * @param to represents e-mail address of a recipient
     * @param email is an actual email letter
     * @throws SendFailedException
     */
    void send(String to, String email) throws SendFailedException;

   void sendUpdateHome(String to, String email) throws SendFailedException;

    void sendCheckEmail(String to, String email) throws SendFailedException;
}
