package com.softserve.edu.sporthubujp.controller;

import java.io.IOException;

import javax.mail.SendFailedException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.sporthubujp.dto.SubscriptionEmailSaveDTO;
import com.softserve.edu.sporthubujp.service.CommentService;
import com.softserve.edu.sporthubujp.service.SubscriptionEmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class SubscriptionEmailController {
    private final SubscriptionEmailService subscriptionEmailService;

    @Autowired
    public SubscriptionEmailController(SubscriptionEmailService subscriptionEmailService) {
        this.subscriptionEmailService = subscriptionEmailService;
    }

    @PostMapping("/newEmail")
    public ResponseEntity<SubscriptionEmailSaveDTO> addNewEmailSubscription(
            @RequestBody @Valid SubscriptionEmailSaveDTO newEmail)
            throws SendFailedException, IOException {
        log.info(String.format("Add new email to subscription %s", newEmail));
        subscriptionEmailService.sendUpdateHome(newEmail.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionEmailService.addNewEmail(newEmail));
    }
}