package com.softserve.edu.sporthubujp.controller;

import java.security.Principal;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.sporthubujp.service.SubscriptionService;
import com.softserve.edu.sporthubujp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @DeleteMapping("/subscription/{id}/team")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteTeamByIdSubscription(@PathVariable("id") String subscriptionId) {
        log.info("Delete subscription by id {}", subscriptionId);
        subscriptionService.deleteSubscriptionByTeam(subscriptionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
