package com.softserve.edu.sporthubujp.service;

import javax.mail.SendFailedException;

import org.springframework.scheduling.annotation.Async;

public interface EmailSenderService {
    void send(String to, String email) throws SendFailedException;

   void sendUpdateHome(String to, String email) throws SendFailedException;

    void sendCheckEmail(String to, String email) throws SendFailedException;
}
