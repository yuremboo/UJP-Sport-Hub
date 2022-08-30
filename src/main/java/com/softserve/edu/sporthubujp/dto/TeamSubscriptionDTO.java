package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TeamSubscriptionDTO {
    private TeamDTO team;
    private String subscriptionId;

    public TeamSubscriptionDTO(TeamDTO teamsDTO, String subscription) {
        this.team = teamsDTO;
        this.subscriptionId = subscription;
    }
}