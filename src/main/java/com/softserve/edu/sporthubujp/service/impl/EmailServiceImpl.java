package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.service.EmailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            log.info(String.format("Service: sending email message to %s", to));
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            System.out.println(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("sporthub@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(String.format("Service: failed to send email message to %s", to));
            throw new IllegalStateException("failed to send email");
        }
    }
}
