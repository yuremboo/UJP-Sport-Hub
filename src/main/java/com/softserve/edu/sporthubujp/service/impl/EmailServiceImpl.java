package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.service.EmailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailSenderService {

    private final static String EMAIL_SENDING_FAILURE = "Service: failed to send email message to %s";
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) throws SendFailedException {
        try {
            log.info(String.format("Service: sending email message to %s", to));
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email, true);
            helper.setTo(to);
            helper.setFrom("sportshub@gmail.com", "Sports Hub");
            helper.setSubject("Welcome to Sports Hub");

            mailSender.send(mimeMessage);
        } catch (UnsupportedEncodingException | MessagingException e) {
            log.error(String.format(EMAIL_SENDING_FAILURE, to));
            throw new SendFailedException(String.format(EMAIL_SENDING_FAILURE, to));
        }
    }
    @Override
    @Async
    public void sendUpdateHome(String to, String email) throws SendFailedException {
        try {
            log.info(String.format("Service: sending email when update home page by admin to %s", to));
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email, true);
            helper.setTo(to);
            helper.setFrom("sportshub@gmail.com", "Sports Hub");
            helper.setSubject("Welcome to Sports Hub");

            mailSender.send(mimeMessage);
        } catch (UnsupportedEncodingException | MessagingException e) {
            log.error(String.format(EMAIL_SENDING_FAILURE, to));
            throw new SendFailedException(String.format(EMAIL_SENDING_FAILURE, to));
        }
    }
    @Override
    @Async
    public void sendCheckEmail(String to, String email) throws SendFailedException {
        try {
            log.info(String.format("Service: check email to reset password to %s", to));
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email, true);
            helper.setTo(to);
            helper.setFrom("sportshub@gmail.com", "Sports Hub");
            helper.setSubject("Welcome to Sports Hub");

            mailSender.send(mimeMessage);
        } catch (UnsupportedEncodingException | MessagingException e) {
            log.error(String.format(EMAIL_SENDING_FAILURE, to));
            throw new SendFailedException(String.format(EMAIL_SENDING_FAILURE, to));
        }
    }
}
