package com.softserve.edu.sporthubujp.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.dto.SubscriptionDTO;
import com.softserve.edu.sporthubujp.dto.SubscriptionSaveDTO;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.service.SubscriptionService;
import com.softserve.edu.sporthubujp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @DeleteMapping("/{id}/team")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteTeamByIdSubscription(@PathVariable("id") String subscriptionId) {
        log.info("Delete subscription by id {}", subscriptionId);
        subscriptionService.deleteSubscriptionByTeam(subscriptionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping()
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<SubscriptionSaveDTO> postSubscription(@RequestBody @Valid SubscriptionSaveDTO newSubscription) {

        log.info("Post subscription");
        return ResponseEntity.status(HttpStatus.OK).body(
            subscriptionService.postSubscription(newSubscription));
    }
}
